package competition.race9.A;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int line = in.nextInt();
        int capacity = in.nextInt();
        Browser browser = new Browser(capacity);

        for (int i = 0; i < line; i++) {
            browser.browse(in.next());
        }
    }

    public static class Browser {

        public List<String> cache;
        public int capacity;

        public Browser(int capacity) {
            cache = new LinkedList<String>();
            this.capacity = capacity;
        }

        public void browse(String url) {
            if (cache.contains(url)) {
                cache.remove(url);
                cache.add(url);
                System.out.println("Cache");
                return;
            }

            if (cache.size() >= capacity) {
                cache.remove(0);
            }
            cache.add(url);
            System.out.println("Internet");
        }

    }

}
