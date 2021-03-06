package pl.koziolekweb.java4people.ui.actions;

import pl.koziolekweb.java4people.data.Book;
import pl.koziolekweb.java4people.data.BookProvider;
import pl.koziolekweb.java4people.ui.BookForm;
import pl.koziolekweb.java4people.ui.BookTable;

import com.google.inject.Inject;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class BookDeleteListenerImpl implements DeleteListener {

	@Inject
	private BookForm bookForm;
	
	@Inject
	private BookTable books;
	
	@Inject
	private BookProvider bookProvider;
	
	public void buttonClick(ClickEvent event) {
		Book book = bookForm.getBook();
		bookProvider.delete(book);
		books.removeBook(book);
		bookForm.reset();
	}

}
