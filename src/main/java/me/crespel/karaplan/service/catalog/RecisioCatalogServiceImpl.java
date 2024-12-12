package me.crespel.karaplan.service.catalog;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import me.crespel.karaplan.model.CatalogArtist;
import me.crespel.karaplan.model.CatalogSelection;
import me.crespel.karaplan.model.CatalogSelectionList;
import me.crespel.karaplan.model.CatalogSelectionType;
import me.crespel.karaplan.model.CatalogSong;
import me.crespel.karaplan.model.CatalogSongFileList;
import me.crespel.karaplan.model.CatalogSongList;
import me.crespel.karaplan.model.CatalogSongListType;
import me.crespel.karaplan.service.CatalogService;

@Primary
@Service("recisioCatalog")
public class RecisioCatalogServiceImpl implements CatalogService {

	private final CatalogService karafunWebCatalog;
	private final CatalogService kvCatalog;

	public RecisioCatalogServiceImpl(@Qualifier("karafunWebCatalog") CatalogService karafunWebCatalog, @Qualifier("kvCatalog") CatalogService kvCatalog) {
		this.karafunWebCatalog = karafunWebCatalog;
		this.kvCatalog = kvCatalog;
	}

	@Override
	public CatalogArtist getArtist(long artistId) {
		return kvCatalog.getArtist(artistId);
	}

	@Override
	public CatalogSong getSong(long songId) {
		return getSong(songId, null);
	}

	@Override
	public CatalogSong getSong(long songId, Locale locale) {
		return karafunWebCatalog.getSong(songId, locale);
	}

	@Override
	public CatalogSongList getSongList(CatalogSongListType type, String filter, Integer limit, Long offset) {
		return getSongList(type, filter, limit, offset, null);
	}

	@Override
	public CatalogSongList getSongList(CatalogSongListType type, String filter, Integer limit, Long offset, Locale locale) {
		switch (type) {
		case query:
		case artist:
			return kvCatalog.getSongList(type, filter, limit, offset, locale);
		default:
			return karafunWebCatalog.getSongList(type, filter, limit, offset, locale);
		}
	}

	@Override
	public CatalogSongFileList getSongFileList(long songId) {
		return getSongFileList(songId, null);
	}

	@Override
	public CatalogSongFileList getSongFileList(long songId, Locale locale) {
		return kvCatalog.getSongFileList(songId, locale);
	}

	@Override
	public CatalogSelection getSelection(CatalogSelectionType type, Long selectionId) {
		return getSelection(type, selectionId, null);
	}

	@Override
	public CatalogSelection getSelection(CatalogSelectionType type, Long selectionId, Locale locale) {
		return karafunWebCatalog.getSelection(type, selectionId, locale);
	}

	@Override
	public CatalogSelectionList getSelectionList(CatalogSelectionType type) {
		return getSelectionList(type, null);
	}

	@Override
	public CatalogSelectionList getSelectionList(CatalogSelectionType type, Locale locale) {
		return karafunWebCatalog.getSelectionList(type, locale);
	}

}
