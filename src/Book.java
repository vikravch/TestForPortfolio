/*класс, хранящий данные об одной книге*/
public class Book {
	
	// (0 - в библиотеке, 1 - выдана, 2 - требует восстановления)
	public final static int IN_LIBRARY = 0;
	public final static int IN_USE = 1;
	public final static int NEED_TO_REPAIRE = 2;
	
	// накопитель, используем при генерации следующего id нового объекта
	public static int nextId = 0;
	// поля
	private int id;
	private String name;
	private int status;
	// конструкторы
	// полный конструктор, используем при восстановлении объекта с файла
	// 8;Java8;0;
	public Book(int id, String name, int status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	}
	// используем при вводе объекта с консоли
	public Book(String name, int status) {
		super();
		this.id = nextId;
		this.name = name;
		this.status = status;
		
		nextId++;
	}
	// геттер
	public int getId() {
		return this.id;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	// to string... - нужен для печати объекта
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", status=" + status + "]";
	}
	// функция сериализации - превращение объекта в строку формата csv
	public String toCSV() {
		//      0       1        2
		return id+";"+name+";"+status+";\n";
	}
	// 12;Java;1; -> Book
	// функция десериализации - превращение строки формата csv в объект
	public static Book fromCSV(String csvBook) {
		String[] dataArr = csvBook.split(";");
		int id = Integer.parseInt(dataArr[0]);
		String name = dataArr[1];
		int status = Integer.parseInt(dataArr[2]);
		return new Book(id,name,status);
	}
}

