import java.util.List;
import java.util.ArrayList;

abstract class Element {
    // This abstract class will be inherited by Image, Paragraph, and Table
    public abstract String toString();
}

class Author {
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

class Book {
    private String title;
    private Author author;
    private List<Chapter> chapters;

    public Book(String title) {
        this.title = title;
        this.chapters = new ArrayList<>();
    }

    public void addAuthor(Author author) {
        this.author = author;
    }

    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public int createChapter(String title) {
        Chapter chapter = new Chapter(title);
        this.chapters.add(chapter);
        return this.chapters.size() - 1;
    }

    public Chapter getChapter(int index) {
        if (index >= 0 && index < chapters.size()) {
            return chapters.get(index);
        }
        return null;
    }
}

class Chapter {
    private String title;
    private List<SubChapter> subChapters;

    public Chapter(String title) {
        this.title = title;
        this.subChapters = new ArrayList<>();
    }

    public void addSubChapter(SubChapter subChapter) {
        subChapters.add(subChapter);
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<SubChapter> getSubChapters() {
        return subChapters;
    }

    public int createSubChapter(String title) {
        SubChapter subChapter = new SubChapter(title);
        this.subChapters.add(subChapter);
        return this.subChapters.size() - 1;
    }

    public SubChapter getSubChapter(int index) {
        if (index >= 0 && index < subChapters.size()) {
            return subChapters.get(index);
        }
        return null;
    }
}

class SubChapter {
    private String title;
    private List<Element> elements; // Store all elements in a unified list

    public SubChapter(String title) {
        this.title = title;
        this.elements = new ArrayList<>();
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public void createNewParagraph(String text) {
        Paragraph paragraph = new Paragraph(text);
        this.elements.add(paragraph);
    }

    public void createNewImage(String imageURL) {
        Image image = new Image(imageURL);
        this.elements.add(image);
    }

    public void createNewTable(String title) {
        Table table = new Table(title);
        this.elements.add(table);
    }

    public void print() {
        System.out.println("SubChapter: " + title);
        for (Element element : elements) {
            System.out.println(element);
        }
    }
}

class Image extends Element {
    private String imageURL;

    public Image(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Image with name: " + imageURL;
    }
}

class Paragraph extends Element {
    private String text;

    public Paragraph(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

class Table extends Element {
    private String title;

    public Table(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Table with name: " + title;
    }
}

public class Main {
    public static void main(String[] args) {
        Book discoTitanic = new Book("Disco Titanic");
        Author author = new Author("Radu Pavel Gheo");
        discoTitanic.addAuthor(author);
        int indexChapterOne = discoTitanic.createChapter("Capitolul 1");
        Chapter chp1 = discoTitanic.getChapter(indexChapterOne);
        int indexSubChapterOneOne = chp1.createSubChapter("Subcapitolul 1.1");
        SubChapter scOneOne = chp1.getSubChapter(indexSubChapterOneOne);
        scOneOne.createNewParagraph("Paragraph 1");
        scOneOne.createNewParagraph("Paragraph 2");
        scOneOne.createNewParagraph("Paragraph 3");
        scOneOne.createNewImage("Image 1");
        scOneOne.createNewParagraph("Paragraph 4");
        scOneOne.createNewTable("Table 1");
        scOneOne.createNewParagraph("Paragraph 5");
        scOneOne.print();
    }
}
