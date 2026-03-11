package edu.eci.arsw.tablero.repository;

import java.util.Set;

import edu.eci.arsw.tablero.model.Board;

public interface BoardRepository {

    void saveBoard(Board board);

    Board getBoard(String author, String name);

    Set<Board> getBoardsByAuthor(String author);

    Set<Board> getAllBoards();

    void addPoint(String author, String name, float x, float y, String color);

    void clearBoard(String author, String name);

}