import java.util.*;

@SuppressWarnings("ALL")
public class Main {
    public static void main(String[] args) {
        lists();
        equality();
        sets();
        maps();
        autoboxing();
    }

    private static void lists() {
        chapter("Lists");
        System.out.println("List is an indexed collection");

        arrayList();
        linkedList();
        listInterface();
        iterators();
    }

    private static void arrayList() {
        section("Class ArrayList<E>");
        System.out.println("Array-based (potentially-)infinite lists");

        System.out.println("Type arguments");
        ArrayList<String> strings = new ArrayList<String>();

        System.out.println("Adding elements");
        strings.add("a");
        strings.add("bc");
        strings.add("def");

        System.out.println("Size: " + strings.size());

        System.out.println("Contents: " + strings);

        System.out.println("Indexed access");
        for (int i = 0; i < strings.size(); i++) {
            System.out.println("    " + i + " " + strings.get(i));
        }

        System.out.println("indexOf");
        System.out.println("    bc: " + strings.indexOf("bc"));
        System.out.println("    zz: " + strings.indexOf("zz"));

        strings.add("a");
        System.out.println("Removing elements");
        System.out.println("   remove(...) returns whether element was actually removed");
        System.out.println("   first instance of the element is removed: " + strings.remove("a"));
        System.out.println("   missing elements are not removed: " + strings.remove("z"));
        System.out.println(strings);
    }


    private static void linkedList() {
        section("Class LinkedList<E>");
        System.out.println("Double linked list-based (potentially-)infinite lists");
        System.out.println("Pretty slow and almost useless");
        System.out.println("Think twice before using it");

        System.out.println("Type arguments");
        LinkedList<String> strings = new LinkedList<String>();

        System.out.println("Adding elements");
        strings.add("a");
        strings.add("bc");
        strings.add("def");

        System.out.println("Size: " + strings.size());

        System.out.println("Contents: " + strings);

        System.out.println("Indexed access");
        System.out.println("  Takes linear time");
        for (int i = 0; i < strings.size(); i++) {
            System.out.println("    " + i + " " + strings.get(i));
        }

        System.out.println("indexOf");
        System.out.println("    bc: " + strings.indexOf("bc"));
        System.out.println("    zz: " + strings.indexOf("zz"));

        System.out.println("Removing elements");
        strings.remove("a");
        strings.remove("z");
        System.out.println(strings);
    }

    private static void listInterface() {
        section("Interface List<E>");

        System.out.println("Abstration over list implementation");

        System.out.println("Sometimes we know exact class");
        List<String> arrayList = new ArrayList<String>();
        List<String> linkedList = new ArrayList<String>();

        System.out.println("Sometimes not");
        String[] array = new String[]{"a", "bc", "def"};
        List<String> arrayAsList = Arrays.asList(array);

        List<String> emptyList = Collections.emptyList();
        List<String> singletonList = Collections.singletonList("hello");
        List<String> immutableList = List.of("hello", "world");

        System.out.println("Nevertheless we could write code agains it");
        fill(arrayList);
        fill(linkedList);

        dump("arrayList", arrayList);
        dump("linkedList", linkedList);
        dump("arrayAsList", arrayAsList);
        dump("emptyList", emptyList);
        dump("singletonList", singletonList);
        dump("immutableList", immutableList);

        System.out.println("Sometimes lists have fixed size");
        try {
            arrayAsList.set(0, "a"); // ok
            arrayAsList.add("a"); // fail
        } catch (UnsupportedOperationException e) {
            System.out.println("   You may not add or remove elements to Array.asList(...): " + e.getClass().getSimpleName());
        }

        System.out.println("Or completly immutable");
        try {
            singletonList.set(0, "a");
        } catch (UnsupportedOperationException e) {
            System.out.println("   Collections.singletonList(...) is immutable: " + e.getClass().getSimpleName());
        }

        try {
            immutableList.set(0, "a");
        } catch (UnsupportedOperationException e) {
            System.out.println("   List.of(...) is immutable: " + e.getClass().getSimpleName());
        }

        System.out.println("You should (almost) never use concrete collection classes in declaration");
    }

    private static void fill(final List<String> strings) {
        strings.add("a");
        strings.add("bc");
        strings.add("def");
    }

    public static void dump(final String name, List<String> list) {
        System.out.println("Dumping " + name);
        for (String s : list) {
            System.out.println("    " + s);
        }
    }

    private static void iterators() {
        section("Iterators");
        List<String> strings = Arrays.asList("s1", "s23", "s456");

        System.out.println("Extended for");
        for (String string : strings) {
            System.out.println("    " + string);
        }

        System.out.println("Is completly equivalent to code with iterator");
        for (Iterator<String> i = strings.iterator(); i.hasNext(); ) {
            String string = i.next();
            System.out.println("    " + string);
        }

        final Iterator<String> it = Collections.singletonList("hello").iterator();
        System.out.println("Two methods of iterator");
        System.out.println("   hasNext() -- check whether more elements are available: " + it.hasNext());
        System.out.println("   next() -- retrieve next element: " + it.next());
        try {
            System.out.println("   hasNext() returns false when no more elements are available: " + it.hasNext());
            it.next();
        } catch (NoSuchElementException e) {
            System.out.println("   and next() throws "  + e.getClass().getSimpleName());
        }
    }

    private static void equality() {
        chapter("Equality");
        indexOf();
        equalsProperties();
    }

    private static void indexOf() {
        section("How List.indexOf works?");

        List<String> strings = Arrays.asList("a", "bc", "def");
        List<Vector> vectors = Arrays.asList(
                new Vector(1, 2),
                new Vector(3, 4),
                new Vector(5, 6)
        );
        System.out.println("Strings: " + strings);
        System.out.println("Vectors: " + vectors);

        System.out.println("First try");
        System.out.println("    indexOf works for Strings: " + strings.indexOf("bc"));
        System.out.println("    indexOf does not work for Vector: " + vectors.indexOf(new Vector(3, 4)));

        System.out.println("Maybe reference equlity is used?");
        System.out.println("    Notice: all constant 'bc' strings points to the same location: " + (strings.get(1) == "bc"));
        String nonConstantBC = "bcd".substring(0, 2);
        System.out.println("    But not when explicitly asked not to: " + (strings.get(1) == nonConstantBC));
        System.out.println("    But indexOf works for not-the-same-reference Strings: " + strings.indexOf(nonConstantBC));

        System.out.println("equals(...) method");
        System.out.println("    You may define equals(...) method for your classes");
        System.out.println("    Collections framework uses it to compare objects");
        System.out.println("    Reference equality is used by default");
        System.out.println("    Many standard classes (including String) defines custom equals(...) method");

        List<VectorWithEquals> vectorsWithEquals = List.of(
                new VectorWithEquals(1, 2),
                new VectorWithEquals(3, 4),
                new VectorWithEquals(5, 6)
        );
        System.out.println("    indexOf works for VectorWithEquals: " + vectorsWithEquals.indexOf(new VectorWithEquals(3, 4)));
    }

    private static void equalsProperties() {
        section("Required equals(...) properties");
        VectorWithEquals v1 = new VectorWithEquals(1, 2);
        VectorWithEquals v2 = new VectorWithEquals(1, 2);
        VectorWithEquals v3 = new VectorWithEquals(1, 2);
        System.out.println("Reflexivity: " + v1.equals(v1));
        System.out.println("Symmetry: " + (v1.equals(v2) == v2.equals(v1)));
        System.out.println("Transitivity: " + (!(v1.equals(v2) && v2.equals(v3)) || v1.equals(v3)));
        System.out.println("False for .equals(null): " + v1.equals(null));
    }

    private static void sets() {
        chapter("Sets");
        System.out.println("Set does not contain equal elements");

        hashSet();
        linkedHashSet();
        hashCodes();
        hashCodeProperties();
    }

    private static void hashSet() {
        section("Class HashSet<E>");
        Set<String> strings = new HashSet<String>();
        System.out.println("add(...) returns whether elements was added to set");
        System.out.println("    Unique elements are added successfully: " + strings.add("a"));
        System.out.println("    While duplicate are not: " + strings.add("a"));

        System.out.println("HashSet does not preserve insertion order");
        fill(strings);
        dumpSet(strings);
    }

    private static void dumpSet(final Set<String> strings) {
        System.out.println(strings);
        for (String string : strings) {
            System.out.println("    " + string);
        }
    }

    private static void fill(final Set<String> strings) {
        for (int i = 0; i < 10; i++) {
            strings.add("e" + i);
        }
    }

    private static void linkedHashSet() {
        section("Class LinkedHashSet");
        Set<String> strings = new LinkedHashSet<String>();

        System.out.println("LinkedHashSet preserves insertion order");
        fill(strings);
        dumpSet(strings);
    }

    private static void hashCodes() {
        section("Hash codes");

        Set<VectorWithEquals> vectors = new HashSet<VectorWithEquals>();
        vectors.add(new VectorWithEquals(1, 2));
        System.out.println("    equal vectors may be added to hash set: " + vectors.add(new VectorWithEquals(1, 2)));
        System.out.println("    and more: " + vectors.add(new VectorWithEquals(1, 2)));
        System.out.println("    and more: " + vectors.add(new VectorWithEquals(1, 2)));

        System.out.println("But not always");
        for (int i = 4; i < 150_000; i++) {
            if (!vectors.add(new VectorWithEquals(1, 2))) {
                System.out.println("    i = " + i + ": add failed");
            }
        }

        System.out.println("What the hell is going on?");
        System.out.println("    Hash-based collections using hasCode() method to calculate hash coded");
        System.out.println("    It returns (almost) random value by default");
        System.out.println("    So, elements are not even compared");

        System.out.println("Let's define custom hashCode");
        Set<VectorWithHashCode> vectorsWithHashCode = new HashSet<VectorWithHashCode>();
        vectorsWithHashCode.add(new VectorWithHashCode(1, 2));
        System.out.println("    add(...) works as expected for equal values: " + !vectorsWithHashCode.add(new VectorWithHashCode(1, 2)));
        System.out.println("    add(...) works as expected for distinct values: " + vectorsWithHashCode.add(new VectorWithHashCode(10, 20)));

        System.out.println("Always override hashCode() when overriding equals(...)");
    }

    private static void hashCodeProperties() {
        section("hashCode properties");
        VectorWithHashCode v1 = new VectorWithHashCode(1, 2);
        VectorWithHashCode v2 = new VectorWithHashCode(1, 2);

        System.out.println("Equal values should always produce equal hash codes: " + (v1.hashCode() == v2.hashCode()));

        VectorWithHashCode v3 = new VectorWithHashCode(10, 20);
        System.out.println("It's good if distinct values produce distinct hash codes: " + (v1.hashCode() != v3.hashCode()));

        VectorWithHashCode v4 = new VectorWithHashCode(10, 281);
        System.out.println("But number of ints is limited, so it is not always possible: " + (v1.hashCode() == v4.hashCode()));
    }

    private static void maps() {
        chapter("Maps");
        System.out.println("Maps contains values indexed by keys");
        hashMap();
        linkedHashMap();
    }

    private static void hashMap() {
        section("Class HashMap<K, V>");

        Map<String, Vector> map = new HashMap<String, Vector>();
        System.out.println("put(key, value) adds new elements to map and returns previous value");
        System.out.println("    " + map.put("a", new Vector(1, 1)));
        System.out.println("    " + map.put("b", new Vector(2, 2)));
        System.out.println("    " + map.put("a", new Vector(3, 3)));
        System.out.println("    " + map);

        System.out.println("get(key) returns value associated with key");
        System.out.println("    " + map.get("a"));
        System.out.println("    or null if there is none: " + map.get("z"));

        System.out.println("remove(key) removes value associated with key and returns previouse value (if any)");
        System.out.println("    " + map.remove("a"));
        System.out.println("    " + map.remove("z"));

        map.put("a", new Vector(123, 123));
        dumpMap(map);
    }

    private static void dumpMap(final Map<String, Vector> map) {
        System.out.println("Contents: " + map);
        System.out.println("You may iterate over keys:");
        for (String key : map.keySet()) {
            System.out.println("    " + key);
        }

        System.out.println("over values:");
        for (Vector value : map.values()) {
            System.out.println("    " + value);
        }

        System.out.println("or key-value pairs:");
        for (Map.Entry<String, Vector> entry : map.entrySet()) {
            System.out.println("    " + entry.getKey() + " " + entry.getValue());
        }
    }

    private static void linkedHashMap() {
        section("Class LinkedHashMap<K, V>");
        System.out.println("Unlike HashMap, LinkedHashMap preserves insertion order");
        Map<String, Vector> map = new LinkedHashMap<String, Vector>();
        for (int i = 0; i < 5; i++) {
            map.put("k" + i, new Vector(i, i));
        }
        dumpMap(map);
    }

    private static void autoboxing() {
        chapter("Autoboxing");

        int i = 10; // Primitive type
        Integer bi; // Correspondingg wrappe (boxed) type

        System.out.println("There is automatic conversion from primitive type to corresponding wrapper (boxed) type");
        bi = i;

        System.out.println("This is the same as");
        bi = Integer.valueOf(i);

        System.out.println("Reverse conversion is also automatic");
        i = bi;

        System.out.println("This is the same as");
        i = bi.intValue();

        bi= null;
        try {
            i = bi;
        } catch (NullPointerException e) {
            System.out.println("Even " + e.getClass().getSimpleName() + " is thrown");
        }

        Integer bi1 = 10;
        Integer bi2 = 10;
        System.out.println("Sometimes boxed values are equal by reference: " + (bi1 == bi2));

        bi1 = 1000;
        bi2 = 1000;
        System.out.println("Sometimes not: " + (bi1 == bi2));

        System.out.println("Never count on that!");
    }

    public static void section(String name) {
        System.out.println();
        System.out.println("--- " + name);
    }

    public static void chapter(String name) {
        String delimiter = name.replaceAll(".", "=");
        System.out.println();
        System.out.println(delimiter);
        System.out.println(name);
        System.out.println(delimiter);
    }
}
