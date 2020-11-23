import java.io.IOException;

public class Main {
/*		 * 1 -> Add new book \REQUIRE - all fields of Book class\
		 * 2 -> Get info about book \REQUIRE - id:int\
		 * 3 -> Print all library
		 * 0 -> exit from our app
		 * */
	
	// константы для выбора опций
	private static int EXIT_OPTION = 0;
	private static int INPUT_BOOK_OPTION = 1;
	private static int PRINT_ONE_BOOK_OPTION = 2;
	private static int PRINT_LIBRARY_OPTION = 3;
	private static int CHANGE_STATUS_OPTION = 4;
	
	public static void main(String[] args) {
		// создание и восстановление Library
		Library library = new Library();
		library.restoreFromBackup();
		// цикл для организации взаимодействия с пользователем
		while(true) {
			// считывание следующей опции
			int itemValue = getNextOption();
			if(itemValue==EXIT_OPTION) {
				System.out.println("Bye!!!");
				break;
			} else if(itemValue==INPUT_BOOK_OPTION) {
				Book book = getBookFromConsole();
				library.addBook(book);
			} else if(itemValue==PRINT_ONE_BOOK_OPTION) {
				System.out.println("Please input id:");
				int id = IOHelper.readFromConsoleInt();
				library.printBook(id);
			} else if(itemValue==PRINT_LIBRARY_OPTION) {
				library.printLibrary();
			} else if(itemValue==CHANGE_STATUS_OPTION) {
				readStatusInfo(library);
			}
		}
	}

	private static void readStatusInfo(Library library) {
		System.out.println("Please input id:");
		int id = IOHelper.readFromConsoleInt();
		System.out.println("Please input status:");
		System.out.println("0 - in library, 1 - in use, 2 - need to repaire");
		int status = IOHelper.readFromConsoleInt();
		
		library.changeBookStatus(id,status);
	}

	private static Book getBookFromConsole() {
		/*System.out.println("Please input id:");
		int id = IOHelper.readFromConsoleInt();*/
		
		System.out.println("Please input name:");
		// ввод имени книги с консоли
		String name = "";
		try {
			name = IOHelper.readFromConsole();
		} catch (IOException e) {
			System.out.println("Something wrong with console");
		}
		
		// ввод статуса с консоли
		//System.out.println("Please input status:");
		//int status = IOHelper.readFromConsoleInt();
		Book book = new Book(name, Book.IN_LIBRARY);
		return book;
	}
	
	private static int getNextOption() {
		System.out.println("Please select one option:");
		System.out.println("1 -> Add new book \\REQUIRE - all fields of Book class\\");
		System.out.println("2 -> Get info about book \\REQUIRE - id:int\\");
		System.out.println("3 -> Print all library");
		System.out.println("4 -> Change book status \\require: id and newStatus\\");
		System.out.println("0 -> exit from our app");
		int res = IOHelper.readFromConsoleInt();
		return res;
	}

}


/*
 * Домашнее задание:
 *  - протестировать работу проекта, поискать баги
 *  - наполнить базу данных реальными книгами
 *  - перечислить возможные статусы с помощью констант: в классе Book
 *   (0 - в библиотеке, 1 - выдана, 2 - требует восстановления)
 *  - при добавлении новой книги с консоли 
 *  автоматически выставлять статус в библиотеке
 *  - запрограммировать опцию 
 *  "4 -> Change book status 
 *  / require: id and newStatus/ " 
 * 	- добавить isEmpty в restoreFromBackup()
 * */
