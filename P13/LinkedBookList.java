package P13;



public class LinkedBookList {

	private BookNode head;
	private int size;
	
	public LinkedBookList(){
		head = null;
		size = 0;
	}
	
	//returns size of the list
	public int size(){
		return size;
	}
	
	//IMPLEMENT -- adds a book to the end of the linked list !!!
	public void add(Book b){
        BookNode newNode = new BookNode(b, null);

	    if (this.size == 0){

	        head = newNode;
	        size++;
            //System.out.println("added book " + b + " to list. size now is " + this.size());
        } else {


            for (BookNode pos = head; pos != null; pos = pos.getNext()) {

                if (pos.getNext() == null) {
                    pos.setNext(newNode);
                    size++;
                    break;
                    //System.out.println("added book " + b + " to list. size now is " + this.size());
                }
            }
        }


	}
	
	//IMPLEMENT -- adds a book at the specific index,
	//  or at the end if index is greater than size
	public void add(Book b, int index){
        BookNode newNode;
        int counter = -1;
		if (index > size){
		    add(b);
        } else{
		    for (BookNode pos = head; pos != null; pos = pos.getNext()){
		        counter++;
		    if (counter == index-1){
		        newNode = new BookNode(b, pos.getNext());
		        pos.setNext(newNode);

            }



            }
        }


	}




	//IMPLEMENT -- removes a book and returns it, or 
	//	returns null if book is not present
	public Book remove(Book b){

        if (size == 0){
            return null;
        }
        for (BookNode pos = head; pos != null; pos = pos.getNext()){
            if (head.getBook().equals(b)){
                Book b2 = head.getBook();
                head = pos.getNext();
                return b2;
            }
            if (pos.getNext().getBook().equals(b)) {
                if (pos.getNext().getNext() != null) {
                    pos.setNext(pos.getNext().getNext());

                    return pos.getNext().getBook();
                }else{

                    pos.setNext(null);
                }
            }



        }


	    return null;
	}

	//IMPLEMENT -- removes a book at a specific index and returns it, 
	//	or returns null if index is not present
	public Book remove(int index){
        int counter = 0;
        if (size == 0){
            return null;
        }


        for (BookNode pos = head; pos != null; pos = pos.getNext()){

            if (counter == 0 && index == 0){
                Book b2 = head.getBook();
                head = pos.getNext();
                return b2;
            }
            if (index == size && counter == index -1){
                Book b = pos.getBook();
                pos.setNext(null);
                return b;


            }

            if (counter == index-1){
                Book b = pos.getNext().getBook();
                pos.setNext(pos.getNext().getNext());
                return b;
            }



            counter++;

        }
        return null;

	}
	
	//IMPLEMENT -- returns the total number of pages in the linked list
	public int totalPages(){
	    if (size == 0){
	        return 0;
        }
	    int total_pages = 0;
        for (BookNode pos = head; pos != null; pos = pos.getNext()){
            total_pages += pos.getNumPages();
        }



		return total_pages;
	}
	

        public String toString()
        {
                String res = "";
                for (BookNode pos = head; pos != null; pos = pos.getNext()) {
                        if (pos.getBook() == null) {
                                res += "null";
                        } else {
                                res += pos.getBook();
                        }

                        if (pos.getNext() != null) res += "\n";
                }
                return res;
        }

}




