import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
// менеджер коллекции книг
public class Library {
	// название файла-бекапа (базы данных)
	private final String BACKUP_FILE_NAME = "library_db.csv";
	// коллекция для организации группы книг
	private LinkedList<Book> books = new LinkedList<>();
	
	// add book
	// print book
	// get book by id
	// print library
	// change status
	// get book
	// take book
	public void addBook(Book book) {
		books.add(book);
		// перезапись всей коллекции в файл (базу данных)
		notifyDataChanged();
	}
	// распечатка информации о книге по id
	public void printBook(int id) {
		Book book = getBookById(id);
		System.out.println(book);
	}
	// получение объекта Book по id книги
	public Book getBookById(int id) {
		for(Book currentBook:books) {
			if (currentBook.getId()==id) {
				return currentBook;
			}
		}
		return null;
	}
	// распечатка всей коллекции книг
	public void printLibrary() {
		for(Book book:books) {
			System.out.println(book);
		}
	}
	// перезапись данных с коллекции книг в файл (базу данных)
	private void notifyDataChanged() {
		// накопили данные о книгах в строку
		//StringBuilder sb = new StringBuilder();
		String dataForFile = "";
		for(Book book:books) {
			dataForFile = dataForFile + book.toCSV();
			//sb.append(book.toCSV());
		}
		// перезаписали файл с новым состоянием коллекции 
		try {
			//IOHelper.writeToFile(BACKUP_FILE_NAME, sb.toString(), false);
			IOHelper.writeToFile(BACKUP_FILE_NAME, dataForFile, false);
		} catch (IOException e) {
			System.out.println("Wrong connection with database!");
		}
	}
	// восстановление данных Library с файла-бекапа. вызываем при старте сервиса
	public void restoreFromBackup() {
		try {
			// читаем все с бекап файла
			String dataFromFile = IOHelper.readFromFile(BACKUP_FILE_NAME);
			// для навигации по данным с файла организовываем Scanner
			Scanner scannerStr = new Scanner(dataFromFile);
			// пока не дошли до последней строки
			while(scannerStr.hasNextLine()) {
				// читаем следующую строку
				String nextBook = scannerStr.nextLine();
				System.out.println(nextBook);
				// организовываем объект класса Book для коллекции
				Book newBook = Book.fromCSV(nextBook);
				books.add(newBook);
			}
			if(books.size()>0) {
				Book.nextId = books.getLast().getId()+1;				
			}
		} catch (IOException e) {
			System.out.println("Database is empty!");
		}
	}
	
	public void changeBookStatus(int id, int status) {
		Book book = getBookById(id);
		if (book==null) {
			System.out.println("Incorrect id. "
					+ "Please type another!");
		} else {
			book.setStatus(status);
			notifyDataChanged();
		}
	}
}
