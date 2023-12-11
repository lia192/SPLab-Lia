import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

// Interface for the alignment strategy
interface AlignStrategy {
    void render(String text);
}

// Concrete strategy for left-aligned text
class AlignLeft implements AlignStrategy {
    public void render(String text) {
        System.out.println("<< " + text);
    }
}

// Concrete strategy for center-aligned text
class AlignCenter implements AlignStrategy {
    public void render(String text) {
        System.out.println("<<< " + text + " >>>");
    }
}

// Concrete strategy for right-aligned text
class AlignRight implements AlignStrategy {
    public void render(String text) {
        System.out.println(text + " >>");
    }
}

// Abstract Element class
abstract class Element {
    public abstract void print();
}

// Author class
class Author {
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println("Author: " + name);
    }
}

// Book class
class Book {
    private String title;
    private List<Author> authors;
    private List<Element> contents;

    public Book(String title) {
        this.title = title;
        this.authors = new ArrayList<>();
        this.contents = new ArrayList<>();
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public void addContent(Element content) {
        this.contents.add(content);
    }

    public void print() {
        System.out.println("Book: " + title);
        for (Author author : authors) {
            author.print();
        }
        for (Element content : contents) {
            content.print();
        }
    }
}

// Section class
class Section extends Element {
    private String title;
    private List<Element> elements;

    public Section(String title) {
        this.title = title;
        this.elements = new ArrayList<>();
    }

    public void add(Element element) {
        elements.add(element);
    }

    @Override
    public void print() {
        System.out.println("Section: " + title);
        for (Element element : elements) {
            element.print();
        }
    }
}

// Image class
class Image extends Element {
    private String imageURL;

    public Image(String imageURL) {
        this.imageURL = imageURL;
        // Simulate a delay in loading the image
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Image loading was interrupted.");
        }
    }

    @Override
    public void print() {
        System.out.println("Image with URL: " + imageURL);
    }
}

// ImageProxy class
class ImageProxy extends Element {
    private String imageURL;
    private Image realImage;

    public ImageProxy(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public void print() {
        if (realImage == null) {
            realImage = new Image(imageURL);
        }
        realImage.print();
    }
}

// Paragraph class with strategy implementation
class Paragraph extends Element {
    private String text;
    private AlignStrategy alignStrategy;

    public Paragraph(String text) {
        this.text = text;
        this.alignStrategy = new AlignLeft(); // Default alignment
    }

    public void setAlignStrategy(AlignStrategy alignStrategy) {
        this.alignStrategy = alignStrategy;
    }

    @Override
    public void print() {
        if (alignStrategy != null) {
            alignStrategy.render(text);
        } else {
            System.out.println(text);
        }
    }
}

// Table class
class Table extends Element {
    private String title;

    public Table(String title) {
        this.title = title;
    }

    @Override
    public void print() {
        System.out.println("Table with title: " + title);
    }
}

public class Main {
    public static void main(String[] args) {
    Section cap1 = new Section("Capitolul 1");
    Paragraph p1 = new Paragraph("Paragraph 1");
    cap1.add(p1);
    Paragraph p2 = new Paragraph("Paragraph 2");
    cap1.add(p2);
    Paragraph p3 = new Paragraph("Paragraph 3");
    cap1.add(p3);
    Paragraph p4 = new Paragraph("Paragraph 4");
    cap1.add(p4);
    System.out.println("Printing without Alignment");
    System.out.println();
    cap1.print();
    p1.setAlignStrategy(new AlignCenter());
    p2.setAlignStrategy(new AlignRight());
    p3.setAlignStrategy(new AlignLeft());

    System.out.println();
    System.out.println("Printing with Alignment");
    System.out.println();
    cap1.print();
    }
}
