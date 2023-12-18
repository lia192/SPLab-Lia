import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

abstract class Element {
    public abstract void print();
}

class Author {
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println("Author: " + name);
    }
}

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
        System.out.println("Authors:");
        for (Author author : authors) {
            author.print();
        }
        for (Element content : contents) {
            content.print();
        }
    }
}

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

class Image extends Element {
    private String imageURL;

    public Image(String imageURL) {
        this.imageURL = imageURL;
        // Simulate a delay in loading the image
        try {
            TimeUnit.SECONDS.sleep(5);
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

class Paragraph extends Element {
    private String text;

    public Paragraph(String text) {
        this.text = text;
    }

    @Override
    public void print() {
        System.out.println("Paragraph: " + text);
    }
}

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
        long startTime = System.currentTimeMillis();
        ImageProxy img1 = new ImageProxy("Pamela Anderson");
        ImageProxy img2 = new ImageProxy("Kim Kardashian");
        ImageProxy img3 = new ImageProxy("Kirby Griffin");
        Section playboyS1 = new Section("Front Cover");
        playboyS1.add(img1);
        Section playboyS2 = new Section("Summer Girls");
        playboyS2.add(img2);
        playboyS2.add(img3);
        Book playboy = new Book("Playboy");
        playboy.addContent(playboyS1);
        playboy.addContent(playboyS2);
        long endTime = System.currentTimeMillis();
        System.out.println("Creation of the content took " + (endTime - startTime) + " milliseconds");
        startTime = System.currentTimeMillis();
        playboyS1.print();
        endTime = System.currentTimeMillis();
        System.out.println("Printing of the section 1 took " + (endTime - startTime) + " milliseconds");
        startTime = System.currentTimeMillis();
        playboyS1.print();
        endTime = System.currentTimeMillis();
        System.out.println("Printing again the section 1 took " + (endTime - startTime) + " milliseconds");
    }
}
