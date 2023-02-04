package cinema.model;

import io.micrometer.core.lang.NonNull;
import org.apache.logging.log4j.util.PropertySource;

import java.util.Comparator;
import java.util.Objects;

public class Seat implements Comparable<Seat> {
    @NonNull
    private volatile int row;
    @NonNull
    private volatile int column;

    private volatile int price;


    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && column == seat.column && price == seat.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, price);
    }


    @Override
    public int compareTo(Seat s) {
        return Comparator.comparingInt(Seat::getRow).thenComparingInt(Seat::getColumn).compare(this, s);
    }
}
