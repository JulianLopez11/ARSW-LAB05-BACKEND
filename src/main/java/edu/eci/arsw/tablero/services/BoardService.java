package edu.eci.arsw.tablero.services;


import edu.eci.arsw.tablero.model.Board;
import edu.eci.arsw.tablero.repository.BoardRepository;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BoardService {

    private final BoardRepository persistence;

    public BoardService(BoardRepository persistence) {
        this.persistence = persistence;
    }

    public void addNewBlueprint(Board b) {
        persistence.saveBoard(b);
    }
    

    public Set<Board> getAllBoards() {
        return persistence.getAllBoards();
    }

    public Set<Board> getBoardsByAuthor(String author)  {
        return persistence.getBoardsByAuthor(author);
    }

    public Board getBoard(String author, String name)  {
        return persistence.getBoard(author, name);
    }

    public void addPoint(String author, String name, float x, float y, String color) {
        persistence.addPoint(author, name, x, y, color);
    }   

    public void clearBoard(String author, String name) {
        persistence.clearBoard(author, name);
    }
}