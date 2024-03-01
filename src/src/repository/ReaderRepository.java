package src.repository;

import src.model.Book;
import src.model.Reader;
import src.util.MyArrayList;
import src.util.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class ReaderRepository {

    private final MyList<Reader> readers;
    private final AtomicInteger currentId = new AtomicInteger(1);

    public ReaderRepository(MyList<Reader> readers) {
        this.readers = new MyArrayList<>();
        initReaders();
    }

    private void initReaders() {
        Reader reader = new Reader(currentId.getAndIncrement(), "john@gmail.com", "Qwerty12$");
        Reader reader1 = new Reader(currentId.getAndIncrement(), "peter@gmail.com", "12345Qw%");
        Reader reader2 = new Reader(currentId.getAndIncrement(), "paul@gmail.com", "111111Zx!");
        Reader reader3 = new Reader(currentId.getAndIncrement(), "tom@gmail.com", "2222222As!");
        Reader reader4 = new Reader(currentId.getAndIncrement(), "bill4@gmail.com", "qwerty!Q1");

        readers.addAll(reader, reader1, reader2, reader3, reader4);
    }

    public MyList<Reader> getAllReaders() {
        return readers;
    }

    public boolean isReaderEmailExist(String email) {
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i).getEmail().equals(email)) return true;
        }
        return false;
    }

    public Reader addNewReader(String email, String password) {
        Reader reader = new Reader(currentId.getAndIncrement(), email, password);
        if (reader.getEmail() == null || reader.getPassword() == null) return null;
        readers.add(reader);
        return reader;
    }

    public Reader removeReader(int id) {
        Reader reader = findReaderId(id);
        if (reader != null) {
            readers.remove(reader);
        } return reader;
    }

    private Reader findReaderId(int id) {
        for (int i = 0; i < readers.size(); i++) {
            Reader reader = readers.get(i);
            if (reader.getId() == id) {
                return reader;
            }
        }
        return null;
    }


}
