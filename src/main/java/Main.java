import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1. Написать программу для парсинга xml документа.
 * Программа на вход получает строку к папке, где находится документ.
 * Распарсить нужно только один документа - соответственно,
 * предусмотреть различные проверки, например на то, что в папке нет файлов
 * или в папке несколько документов формата xml и другие возможные проверки.
 * Необходимо распарсить xml документ и содержимое тегов line записать в другой документ.
 * Название файла для записи должно состоять из значений тегов и имеет вид: <firstName>_<lastName>_<title>.txt
 */
public class Main {

    static String fileName;
    static String line;
    static String fileXMLPaths;

    public static void main(String[] args) throws JAXBException, IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter path to folder");
            String pathFolder = scanner.nextLine();      // src/main/resources

            getFileXMLPath(pathFolder);
            unmarshal();
            murshal(new Person(line));
        }
    }

    private static void getFileXMLPath(String pathFolder) throws IOException {
        try (Stream<Path> list = Files.list(Paths.get(pathFolder))) {
            Set<Path> filesPaths = list.collect(Collectors.toSet());
            if (filesPaths.size() != 0) {
                fileXMLPaths = filesPaths.stream().findFirst().get().toString();
                System.out.println(fileXMLPaths);
            } else {
                System.out.println("Folder is empty");
            }
        }
    }

    private static void unmarshal() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Person person = (Person) context.createUnmarshaller().unmarshal(new FileReader(fileXMLPaths));
        line = person.getLine();
        fileName = String.join("_", person.getFirstName(), person.getLastName(), person.getTitle());
        System.out.println(person);
    }

    private static void murshal(Person person) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(person, new File("src/main/resources/" + fileName + ".xml"));
    }
}