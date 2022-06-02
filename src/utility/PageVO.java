package utility;

public class PageVO {
   
   private int startPage; //시작페이지
   private int endPage; //끝페이지
   private boolean prev, next; //이전 다음
   private int total; //총레코드 수
   private  Criteria cri; //Criteria 내 변수를 cri란 변수를 사용하여 PageVO에 주입
   
   public PageVO(Criteria cri, int total) { //생성자
      this.cri = cri;
      this.total = total;
      this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10; //Math.ceil:무조건 올림
      this.startPage = this.endPage - 9;
      int realEnd = (int)(Math.ceil((total * 1.0)/cri.getAmount()));
      if(realEnd < this.endPage) {
         this.endPage = realEnd;
         System.out.println("endPage : "+endPage);
      }
      this.prev = this.startPage > 1;
      this.next = this.endPage < realEnd;
   }

   public int getStartPage() {
      return startPage;
   }
   public void setStartPage(int startPage) {
      this.startPage = startPage;
   }
   public int getEndPage() {
      return endPage;
   }
   public void setEndPage(int endPage) {
      this.endPage = endPage;
   }
   public boolean isPrev() {
      return prev;
   }
   public void setPrev(boolean prev) {
      this.prev = prev;
   }
   public boolean isNext() {
      return next;
   }
   public void setNext(boolean next) {
      this.next = next;
   }
   public int getTotal() {
      return total;
   }
   public void setTotal(int total) {
      this.total = total;
   }
   public Criteria getCri() {
      return cri;
   }
   public void setCri(Criteria cri) {
      this.cri = cri;
   }
}