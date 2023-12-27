package me.crespel.karaplan.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.SortComparator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(exclude = { "artist", "styles", "votes", "comments", "playlists", "createdDate", "updatedDate" })
@ToString(of = { "id", "name" })
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "song")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Song implements Comparable<Song>, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true)
	private Long id;

	@Column(name = "CATALOG_ID", unique = true)
	private Long catalogId;

	@NotNull
	@Column(name = "NAME")
	private String name;

	@Column(name = "DURATION")
	private Long duration;

	@Column(name = "YEAR")
	private Integer year;

	@Column(name = "IMAGE")
	private String image;

	@Transient
	private String rights;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "FK_ARTIST", referencedColumnName = "ID")
	@JsonIgnoreProperties({ "songs", "createdDate", "updatedDate" })
	private Artist artist;

	@Transient
	private Set<Style> styles;

	@Column(name = "SCORE")
	private Integer score;

	@Column(name = "SCORE_UP")
	private Integer scoreUp;

	@Column(name = "SCORE_DOWN")
	private Integer scoreDown;

	@OneToMany(mappedBy = "song", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("song")
	@SortComparator(SongVote.OrderByIdDescComparator.class)
	private SortedSet<SongVote> votes = Sets.newTreeSet(SongVote.orderByIdDescComparator);

	@Column(name = "COMMENTS_COUNT")
	private Integer commentsCount;

	@OneToMany(mappedBy = "song", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("song")
	@SortComparator(SongComment.OrderByIdDescComparator.class)
	private SortedSet<SongComment> comments = Sets.newTreeSet(SongComment.orderByIdDescComparator);

	@Column(name = "PLAYLISTS_COUNT")
	private Integer playlistsCount;

	@OneToMany(mappedBy = "key.song", fetch = FetchType.EAGER, orphanRemoval = true) // Association is managed from the Playlist side
	@JsonIgnoreProperties("song")
	@SortComparator(PlaylistSong.OrderByPlaylistAndPositionAndSongComparator.class)
	private SortedSet<PlaylistSong> playlists = Sets.newTreeSet(PlaylistSong.orderByPlaylistAndPositionAndSongComparator);

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Calendar createdDate;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Calendar updatedDate;

	public void updateStats() {
		this.score = (votes != null) ? votes.stream().mapToInt(SongVote::getScore).sum() : 0;
		this.scoreUp = (votes != null) ? votes.stream().mapToInt(SongVote::getScore).filter(score -> score > 0).sum() : 0;
		this.scoreDown = (votes != null) ? Math.abs(votes.stream().mapToInt(SongVote::getScore).filter(score -> score < 0).sum()) : 0;
		this.commentsCount = (comments != null) ? comments.size() : 0;
		this.playlistsCount = (playlists != null) ? playlists.size() : 0;
	}

	@Override
	public int compareTo(Song o) {
		return orderByNameComparator.compare(this, o);
	}

	public static Comparator<Song> orderByIdComparator = new OrderByIdComparator();

	public static class OrderByIdComparator implements Comparator<Song>, Serializable {

		private static final long serialVersionUID = 1L;

		@Override
		public int compare(Song o1, Song o2) {
			return ComparisonChain.start()
					.compare(o1.id, o2.id, Ordering.natural().nullsLast())
					.result();
		}

	}

	public static Comparator<Song> orderByNameComparator = new OrderByNameComparator();

	public static class OrderByNameComparator implements Comparator<Song>, Serializable {

		private static final long serialVersionUID = 1L;

		@Override
		public int compare(Song o1, Song o2) {
			return ComparisonChain.start()
					.compare(o1.name, o2.name, Ordering.natural().nullsLast())
					.compare(o1.id, o2.id, Ordering.natural().nullsLast())
					.result();
		}

	}

}
