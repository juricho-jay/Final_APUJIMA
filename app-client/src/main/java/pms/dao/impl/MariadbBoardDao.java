package pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pms.dao.BoardDao;
import pms.domain.Board;
import pms.domain.Member;
import pms.domain.WhichBoard;

public class MariadbBoardDao implements BoardDao{

  Connection con;

  public MariadbBoardDao(Connection con) {
    this.con = con;
  }
  @Override
  public void insert(Board board) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into pms_board(title,content,member_no,whichboard_no) values(?,?,?,?)")) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, board.getWriter().getNo());
      stmt.setInt(4, board.getWhichBoard());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("게시글 데이터 입력 실패!");
      }
    }
  }



  /*
   * 1.whichBoard의 Domain을 가져오는게 맞는가?
   * => 맞다면 적용을 어떻게 시킬건가..?
   * => 아니라면? 어떻게 게시판을 분류할 것인가. 
   *
   */
  @Override
  public List<Board> findAll() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " b.board_no,"
            + " b.title,"
            + " b.content,"
            + " b.created_dt,"
            + " b.view_cnt,"
            + " m.member_no,"
            + " m.id,"
            + " t.whichboard_no"
            + " from"
            + " apus_board b"
            + " inner join apus_member m on b.member_no=m.member_no "
            + " inner join apus_board_type t on b.whichboard_no=t.whichboard_no"
            + " order by b.board_no desc");
        ResultSet rs = stmt.executeQuery()){

      ArrayList<Board> list = new ArrayList<>();

      while(rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_no"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        board.setRegisteredDate(rs.getDate("created_dt"));
        board.setViewCount(rs.getInt("view_cnt"));

        Member member = new Member();
        member.setNo(rs.getInt("member_no"));
        member.setId(rs.getString("id"));

        WhichBoard whichBoard = new WhichBoard();
        whichBoard.setWhichBoardNo(rs.getInt("whichboard_no"));


        board.setWriter(member);
        list.add(board);
      }
      return list;
    }
  }


  @Override
  public List<Board> findByKeyword(String keyword) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " b.board_no,"
            + " b.title,"
            + " b.content,"
            + " b.created_dt,"
            + " b.view_cnt,"
            + " m.member_no,"
            + " m.id,"
            + " t.whichboard_no"
            + " from"
            + " apus_board b"
            + " inner join apus_member m on b.member_no=m.member_no "
            + " inner join apus_board_type t on b.whichboard_no=t.whichboard_no"
            + " where b.title like (concat('%',?,'%'))"
            + " or b.content like (concat('%',?,'%'))"
            + " or m.id like (concat('%',?,'%'))"
            + " order by b.board_no desc")){

      stmt.setString(1, keyword);
      stmt.setString(2, keyword);
      stmt.setString(3, keyword);

      try (ResultSet rs = stmt.executeQuery()){
        ArrayList<Board> list = new ArrayList<>();

        while(rs.next()) {
          Board board = new Board();
          board.setNo(rs.getInt("board_no"));
          board.setTitle(rs.getString("title"));
          board.setContent(rs.getString("content"));
          board.setRegisteredDate(rs.getDate("created_dt"));
          board.setViewCount(rs.getInt("view_cnt"));

          Member member = new Member();
          member.setNo(rs.getInt("member_no"));
          member.setId(rs.getString("id"));

          WhichBoard whichBoard = new WhichBoard();
          whichBoard.setWhichBoardNo(rs.getInt("whichboard_no"));


          board.setWriter(member);
          list.add(board);
        }
        return list;

      }
    }
  }


  @Override
  public Board findByNo(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " b.board_no,"
            + " b.title,"
            + " b.content,"
            + " b.created_dt,"
            + " b.view_cnt,"
            + " m.member_no,"
            + " m.id,"
            + " t.whichboard_no"
            + " from"
            + " apus_board b"
            + " inner join apus_member m on b.member_no=m.member_no "
            + " inner join apus_board_type t on b.whichboard_no=t.whichboard_no"
            + " where board_no=" + no);
        ResultSet rs = stmt.executeQuery()){

      if(rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_no"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        board.setRegisteredDate(rs.getDate("created_dt"));
        board.setViewCount(rs.getInt("view_cnt"));

        Member member = new Member();
        member.setNo(rs.getInt("member_no"));
        member.setId(rs.getString("id"));

        WhichBoard whichBoard = new WhichBoard();
        whichBoard.setWhichBoardNo(rs.getInt("whichboard_no"));

        board.setWriter(member);

        try(PreparedStatement stmt2 = con.prepareStatement(
            "update apus_board set view_cnt=view_cnt + 1 where board_no=" + no)){
          stmt2.executeUpdate();
        }
        return board;
      }
      return null;
    }
  }

  @Override
  public void update(Board board) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update apus_board set"
            +" title=?,content=?"
            +" where board_no =?")){
      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, board.getNo());
      if (stmt.executeUpdate() == 0) {
        throw new Exception("게시글 데이터 변경 실패!");
      }
    }
  }
  @Override
  public void delete(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from apus_board where board_no=?")) {

      stmt.setInt(1, no);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("게시글 데이터 삭제 실패!");
      }
    }
  }
}

