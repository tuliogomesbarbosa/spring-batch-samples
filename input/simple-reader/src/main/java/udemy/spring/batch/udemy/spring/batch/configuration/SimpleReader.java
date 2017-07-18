package udemy.spring.batch.udemy.spring.batch.configuration;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Iterator;
import java.util.List;

/**
 * Created by tgomes on 7/10/17.
 */
public class SimpleReader implements ItemReader<Integer> {
    private Iterator<Integer> data;

    public SimpleReader(List<Integer> data) {
        this.data = data.iterator();
    }

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (this.data.hasNext()) {
            return this.data.next();
        } else {
            return null;
        }
    }
}
