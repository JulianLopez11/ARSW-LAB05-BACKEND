package edu.eci.arsw.tablero.repository;

import edu.eci.arsw.tablero.model.Board;
import edu.eci.arsw.tablero.model.Point;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryBoardRepository implements BoardRepository {

    private final Map<String, Board> boards = new ConcurrentHashMap<>();

    public InMemoryBoardRepository() {
        Board b1 = new Board("camilo","board1");
        b1.addPoint(new Point(10,10,"#ff0000"));
        b1.addPoint(new Point(20,20,"#ff0000"));

        Board b2 = new Board("juan","board2");
        b2.addPoint(new Point(50,50,"#0000ff"));

        boards.put(keyOf(b1), b1);
        boards.put(keyOf(b2), b2);
    }

    private String keyOf(Board b){
        return b.getAuthor() + ":" + b.getName();
    }

    private String keyOf(String author,String name){
        return author + ":" + name;
    }

    @Override
    public void saveBoard(Board board){
        boards.put(keyOf(board), board);
    }

    @Override
    public Board getBoard(String author,String name){

        return boards.get(keyOf(author,name));
    }

    @Override
    public Set<Board> getBoardsByAuthor(String author){

        return boards.values().stream()
                .filter(b -> b.getAuthor().equals(author))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Board> getAllBoards(){

        return new HashSet<>(boards.values());
    }

        @Override
        public void addPoint(String author,String name,float x,float y,String color){

            Board board = boards.get(keyOf(author,name));

            if(board != null){
                board.addPoint(new Point(x,y,color));
            }
        }

    @Override
    public void clearBoard(String author,String name){

        Board board = boards.get(keyOf(author,name));

        if(board != null){
            board.clear();
        }
    }
}