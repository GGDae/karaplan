package me.crespel.karaplan.model;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatalogSelectionList implements Serializable {

	private static final long serialVersionUID = 1162630846837382774L;

	private CatalogSelectionType type;
	private Set<CatalogSelection> selections;

}
