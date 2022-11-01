package com.jspiders.musicHibernate.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SongDTO {
	@Id
	private int id;
	private String songName;
	private String singerName;
	private String movieName;
	private String composer;
	private String lyrist;
	private double duration;
}
