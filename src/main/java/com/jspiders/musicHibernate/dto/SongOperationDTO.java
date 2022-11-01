package com.jspiders.musicHibernate.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.TransactionException;

public class SongOperationDTO {

	ArrayList<SongDTO> list = new ArrayList<SongDTO>();
	static SongDTO song;
	static Scanner sc = new Scanner(System.in);

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

	private static void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory("musicHibernate");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	private static void closeConnection() {

		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
		if (entityManager != null) {
			entityManager.close();
		}
		if (entityTransaction != null) {
			try {
				entityTransaction.rollback();

			} catch (TransactionException e) {
				System.out.println("Transaction already Commit");
			}
		}
	}

	public static void chooseSongToPlay() {
		openConnection();
		entityTransaction.begin();

		String songDTO = "select id from SongDTO";
		while (songDTO.isEmpty()) {
			System.out.println("\nno songs present\n");
			addSongs();
		}

		displayAllSongs();
		System.out.println("Select song to Play ");
		int select  = sc.nextInt();
		System.out.println(" Playing song " +select);
	}

	public static void playAllSongs() {
		openConnection();
		entityTransaction.begin();
		String songDTO = "select songName from SongDTO";
		if (songDTO.isEmpty()) {
			System.out.println("Add songs then play..");
			addSongs();
		}
		System.out.println("Playing All songs ");
		javax.persistence.Query query = entityManager.createQuery(songDTO);

		List song = query.getResultList();

		for (Object SongDTO : song) {
			System.out.println(SongDTO);
		}

	}

	public static void playRandomSongs() {
		openConnection();
		entityTransaction.begin();
		String songDTO = "select songName from SongDTO";
		if (songDTO.isEmpty()) {
			System.out.println("Add songs then play..");
			addSongs();
		}
		System.out.println("Playing Random songs ");
		double number = Math.random();
		int random = (int) (number * 10) + 1;
		if (random > songDTO.length()) {
			random = 1;
		}
		System.out.println("Playing song ");
	}

	// add song
	public static void addSongs() {
		try {
			openConnection();
			entityTransaction.begin();

			SongDTO songDTO = new SongDTO();
			System.out.println("Enter the Id");
			songDTO.setId(sc.nextInt());
			System.out.println("Enter the Song Name");
			songDTO.setSongName(sc.next());
			System.out.println("Enter the Singer Name");
			songDTO.setSingerName(sc.next());
			System.out.println("Enter the Movie Name");
			songDTO.setMovieName(sc.next());
			System.out.println("Enter the Lyrist");
			songDTO.setLyrist(sc.next());
			System.out.println("Enter the Composer");
			songDTO.setComposer(sc.next());
			System.out.println("Enter the Duration");
			songDTO.setDuration(sc.nextDouble());
			System.out.println(" Song added successfully");

			entityManager.persist(songDTO);
			entityTransaction.commit();
		} catch (Exception e) {

		}
		closeConnection();

	}

	// remove song
	public static void removeSongs() {
		try {
			openConnection();

			entityTransaction.begin();
			System.out.println("Enter the remove Id");
			SongDTO song = entityManager.find(SongDTO.class, sc.nextInt());
			entityManager.remove(song);

			System.out.println("Song is deleted Successfully");

			entityTransaction.commit();
		} catch (Exception e) {

		}
		closeConnection();

	}

	// edit song
	public static void editSongs() {
		try {
			openConnection();

			entityTransaction.begin();

			String songDTO = "update SongDTO set songname='Black Dom' where id=2";
			// String songDTO = "update SongDTO songDTO.setSongName('sc.next())' where
			// songDTO.getId(sc.nextInt())";
			javax.persistence.Query query = entityManager.createQuery(songDTO);
			int executeUpdate = query.executeUpdate();

			System.out.println(executeUpdate + " Edit Successfully ");

			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		closeConnection();

	}

	// display

	public static void displayAllSongs() {
		openConnection();

		entityTransaction.begin();

		System.out.println("Music Library");

		String songDTO = "select songName from SongDTO";

		javax.persistence.Query query = entityManager.createQuery(songDTO);

		List song = query.getResultList();

		for (Object SongDTO : song) {
			System.out.println(SongDTO);
		}
		entityTransaction.commit();

		closeConnection();

	}
}
