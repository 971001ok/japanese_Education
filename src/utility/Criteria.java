package utility;

public class Criteria { //4개의 값을 하나의 객체로 묶어서 데이터를 전달하기 위해 만든 클래스
   
   //페이징 처리를 위해서 필요한 파라미터는 1)페이지번호, 2)한 페이지당 보여줄 레코드의 개수가 필요하다.
   private int pageNum;
   private int amount;
   
   private String type; //검색할때 제목, 내용 저장
   private String keyword; //검색단어
   
   public Criteria() {
      this(1,10);
   } //기본 생성자
   
   public Criteria(int pageNum, int amount) { //1과 10이 int pageNum, int amount 차례로 저장됨
      this.pageNum = pageNum;
      this.amount = amount;
   }

   public int getPageNum() {
      return pageNum;
   }
   public void setPageNum(int pageNum) {
      this.pageNum = pageNum;
   }
   public int getAmount() {
      return amount;
   }
   public void setAmount(int amount) {
      this.amount = amount;
   }
   public String getType() {
      return type;
   }
   public void setType(String type) {
      this.type = type;
   }
   public String getKeyword() {
      return keyword;
   }
   public void setKeyword(String keyword) {
      this.keyword = keyword;
   }
}