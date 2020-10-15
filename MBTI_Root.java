package MBTI_Experiment_2;

import java.util.*;

public class MBTI_Root {

    protected int counter = 0;
    protected List<String> search_results = new ArrayList<>();
    protected List<String> selected_functions = new ArrayList<>();
    protected List<Integer> positions = new ArrayList<>();
    protected List<String> functions_array_list = new ArrayList<>();
    protected char[] functions_array = new char[] {'*', '*', '*', '*'};
    protected double[] percentages = new double[] {0.0, 0.0};
    protected String[] types_array = new String[] {
            "INTJ", "INTP", "ENTJ", "ENTP",
            "INFJ", "INFP", "ENFJ", "ENFP",
            "ISTJ", "ISFJ", "ESTJ", "ESFJ",
            "ISTP", "ISFP", "ESTP", "ESFP"};
    protected double[] male_percentages = new double[] {
            3.3, 4.8, 2.7, 4.0,
            1.2, 4.1, 2.7, 6.4,
            16.4, 8.1, 11.2, 7.5,
            8.5, 7.6, 5.6, 6.9};
    protected double[] female_percentages = new double[] {
            0.9, 1.7, 0.9, 2.4,
            1.6, 4.6, 3.3, 9.7,
            6.9, 19.4, 6.3, 16.9,
            2.3, 9.9, 3.0, 10.1};

    /*protected String[] nicknames = new String[] {
            "Architect", "Logician", "Commander", "Debater",
            "Advocate", "Mediator", "Protagonist", "Campaigner",
            "Logistician", "Defender", "Executive", "Consul",
            "Virtuoso", "Adventurer", "Entrepreneur", "Entertainer"};*/

    private String[] judging_c_functions = new String[] {"Ni", "Si", "Fe", "Te"};
    private String[] perceiving_c_functions = new String[] {"Se", "Ne", "Ti", "Fi"};

    private void print_line() {
        System.out.println("------------------------------------------------");
    }

    private void print_new_line() {
        System.out.println("\n------------------------------------------------");
    }

    private Scanner input() {
        return new Scanner(System.in);
    }

    public MBTI_Root() {
        boolean valid = true;       // Resets boolean at the start of function
        while (valid) {
            print_new_line();
            System.out.println("Below is a list of possible functions. ");
            print_line();
            System.out.println(
                    " 1.  View Guide (Help) " +
                    "\n 2.  Run Gender Distribution " +
                    "\n 3.  Run Distribution by Letter " +
                    "\n 4.  Retrieve Function Distribution  " +
                    "\n 5.  Run Function Search " +
                    "\n 6.  Retrieve Type Information " +
                    "\n 7.  Retrieve Distribution Information " +
                    "\n 0.  Terminate Program ");
            print_line();
            System.out.println("Please Enter the Number of the Desired Function. ");
            print_line();
            System.out.println();
            int option = input().nextInt();

            if (option == 1) {
                boolean cont = true;
                while (cont) {
                    System.out.println("\nWhich function would you like information on? Enter number below. (0 - 7) ");
                    int info = input().nextInt();
                    Function_Information(info);
                    cont = binary_choice("Enter more inputs?");
                }
            }

            else if (option == 2) {
                boolean cont = true;
                while (cont) {
                    System.out.println("\nEnter the four letter MBTI code for the desired type. ");
                    String type = input().nextLine().toUpperCase();
                    MBTI_Function_1(type);
                    double male = percentages[0];
                    double female = percentages[1];
                    double combined = Math.round((male + female) / 2);
                    if (percentages[0] != 0.0 && percentages[1] != 0.0) {
                        System.out.println("\n Percentage of males that are " + type.toUpperCase() + "'s: " + male + "%");
                        System.out.println(" Percentage of females that are " + type.toUpperCase() + "'s: " + female + "%");
                        System.out.println(" Percentage of population that are " + type.toUpperCase() + "'s: " + combined + "%");
                        display(to_array_list(to_list(type)));
                    }
                    cont = binary_choice("Enter more inputs?");
                }
            }

            else if (option == 3) {
                boolean cont = true;
                while (cont) {
                    System.out.println("\nEnter desired letter-functions. ");
                    String functions = input().nextLine().toUpperCase();
                    MBTI_Function_2(functions);
                    cont = binary_choice("Enter more inputs?");
                }
            }

            else if (option == 4) {
                boolean cont = true;
                while (cont) {
                    System.out.println("\nEnter four letter MBTI code for the desired type. ");
                    String type = input().nextLine().toUpperCase();
                    MBTI_Function_3(type);
                    cont = binary_choice("Enter more inputs?");
                }
            }

            else if (option == 5) {
                boolean cont = true;
                while (cont) {
                    System.out.println("\nThis program can run a search query with either a custom depth or a default depth of two functions.");
                    boolean answer = binary_choice("Run custom search query?");
                    if (answer) { System.out.println("\nRunning custom query."); }
                    else { System.out.println("\nRunning default query."); }
                    System.out.println("\nThis program searches for any one of the following eight functions: \n");
                    for (int position = 0; position < 4; position++) {
                        if (position < 2) {
                            System.out.println("| " + judging_c_functions[position] + " | " + perceiving_c_functions[position] + " |");
                        } else {
                            System.out.println("| " + perceiving_c_functions[position] + " | " + judging_c_functions[position] + " |");
                        }
                    }
                    System.out.println("\nEnter desired cognitive function. ");
                    String function = input().nextLine().toUpperCase();
                    String[] function_array = new String[1];
                    function_array[0] = function;
                    String function_string = function_conversion(function_array);
                    if (function_string == null) { break; }
                    if (answer) {
                        System.out.println("\nEnter search start area (1 - 4). ");
                        int start = input().nextInt();
                        System.out.println("\nEnter search end area (1 - 4). ");
                        int end = input().nextInt();
                        if (start < end && 1 <= start && end <= 4) {
                            System.out.println("\nSearching for function " + function_string +
                                    " through cognitive function depths " + start + " and " + end + ". ");
                            ArrayList<String> type_list = MBTI_Function_4(function, start, end);
                            System.out.println("\n" + function_string + " is found at depths " + start +
                                    " and " + end + " in the following types: " + type_list);
                            display(type_list);
                        } else if (start == end) {
                            System.out.println("\nSearching for function " + function_string +
                                    " through cognitive function depth " + start + ". ");
                            ArrayList<String> type_list = MBTI_Function_4(function, start, end);
                            System.out.println("\n" + function_string + " is found at depth " + start + " in the following types: " + type_list);
                            display(type_list);

                        } else {
                            System.out.println("\nInvalid depths entered.");
                        }
                    } else {
                        System.out.println("\nSearching for function " + function_string + "... ");
                        ArrayList<String> type_list = MBTI_Function_4(function, 1, 2);
                        System.out.println("\n" + function_string + " is found in the following types: " + type_list);
                        display(type_list);
                    }
                    cont = binary_choice("Enter more inputs?");
                }
            }

            else if (option == 6) {
                boolean cont = true;
                while (cont) {
                    System.out.println("\nWork-in-progress. Please see 16personalities.com for personality type details.");
                    System.out.println("\nPatience is a virtue.");
                    cont = binary_choice("Relay message again?");
                }
            }

            else if (option == 7) {
                boolean cont = true;
                while (cont) {
                    System.out.println("\nData retrieved from careerplanner.com and 16personalities.com.");
                    cont = binary_choice("Relay distribution sources again?");
                }
            }

            else if (option == 0) {
                boolean cont = binary_choice("Terminating program. Are you sure?");
                if (cont) {
                    System.out.println("\nProgram Terminated");
                    System.exit(0);
                } else {
                    System.out.println("\nReturning to root...");
                }
            }

            else {
                System.out.println("\nInvalid Input");
            }
            valid = binary_choice("Return to Root?");
            if (!valid) {
                boolean check = binary_choice("Terminating program. Are you sure?");
                if (!check) {
                    valid = true;
                    System.out.println("\nReturning to root...");
                }
            }
        }
        System.out.println("\nProgram Terminated");
        System.exit(0);
    }

    public void Function_Information(int function_num) {
        String function_name = null;
        String function_info = null;
        String function_message = null;
        if (function_num == 1) {
            function_name = "View Guide (Help)";
            function_info = "Displays information for each function.";
            function_message = "The worst questions answer themselves, don't they?";
        }
        else if (function_num == 2) {
            function_name = "Run Gender Distribution";
            function_info = "Shows the distribution, in percent, of an entered type among males, females, and the general population.";
            function_message = "Only two; no more, no less. (Kudos if you recognize that quote).";
        }
        else if (function_num == 3) {
            function_name = "Run Distribution by Letter";
            function_info = "Shows distribution of any given letter-functions among types.";
            function_message = "This took the longest. Appreciate it.";
        }
        else if (function_num == 4) {
            function_name = "Retrieve Function Distribution";
            function_info = "Displays function distribution for an inputted type.";
            function_message = "Like 5, but reversed. In case you were wondering, that was super helpful.";
        }
        else if (function_num == 5) {
            function_name = "Run Function Search";
            function_info = "Retrieves and displays types that use an inputted function.";
            function_message = "Like 4, but reversed. Shocking, I know.";
        }
        else if (function_num == 6) {
            function_name = "Retrieve Type Information";
            function_info = "Displays a bit of information about an inputted type. This is a work-in-progress.";
            function_message = "You know what they say about procrastination? I'll tell you later.";
        }
        else if (function_num == 7) {
            function_name = "Retrieve Distribution Information";
            function_info = "Displays information sources.";
            function_message = "For those who cared, and those who didn't.";
        }
        else if (function_num == 0) {
            function_name = "Terminate Program";
            function_info = "Ends and exits the program.";
            function_message = "As they say, all good things come to an end, and all else must be forced to do so.";
        }

        if (function_name != null) {
            System.out.println("\nEntered " + function_num + ": " + function_name);
            System.out.println("\n" + function_info);
            if (function_message != null) {
                System.out.println(function_message);
            }
        } else {
            System.out.println("\nInvalid Input");
        }
    }

    public void MBTI_Function_1(String type) {
        try {
            int location = Arrays.asList(types_array).indexOf(type);
            double male = male_percentages[location];
            double female = female_percentages[location];
            percentages[0] = male;
            percentages[1] = female;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\nInvalid input. MBTI type " + type.toUpperCase() + " does not exist.");
            percentages[0] = 0.0;
            percentages[1] = 0.0;
        }
    }

    public void MBTI_Function_2(String functions) {
        List<String>types_array_list = reset_types_array_list();
        reset_search_results();
        reset_counter();
        reset_functions_array();
        selected_functions.clear();
        functions_array_list.clear();
        positions.clear();

        if (functions.contains("I")) {add_function("Introverted", 'I');}
        if (functions.contains("E")) {add_function("Extroverted", 'E');}
        if (functions.contains("N")) {add_function("Intuitive", 'N');}
        if (functions.contains("S")) {add_function("Sensing", 'S');}
        if (functions.contains("T")) {add_function("Thinking", 'T');}
        if (functions.contains("F")) {add_function("Feeling", 'F');}
        if (functions.contains("J")) {add_function("Judging", 'J');}
        if (functions.contains("P")) {add_function("Perceiving", 'P');}

        if (selected_functions.size() == 0) {
            System.out.println("\nInvalid input. " + functions.toUpperCase() + " is not a function.");
        } else {
            System.out.println("\nInputted function(s): " + selected_functions);
            Collections.sort(positions);
            Object[] positions_array = positions.toArray();
            for (int i = 0; i < positions.size() - 1; i++) {
                if (positions_array[i].equals(positions_array[i + 1])) {
                    System.out.println("\nContradictory functions detected: (" +
                            selected_functions.get(i) + ", " + selected_functions.get(i + 1) +
                            "). Will utilize latter-listed contradictory function (" +
                            selected_functions.get(i + 1) + ").");
                }
            }
            search(types_array_list, functions_array, functions.length());
            System.out.println("\nTypes that use inputted functions: " + search_results);
            display(to_array_list(search_results));
        }
    }

    public void MBTI_Function_3(String type) {
        reset_counter();
        String[] functions = function_assignment(type);
        String function_names = function_conversion(functions);
        System.out.println("\n" + type + "'s utilize the following functions: " + Arrays.toString(functions));
        System.out.println(function_names);

    }

    public ArrayList<String> MBTI_Function_4(String inputted_function, int start, int end) {
        ArrayList<String[]> list_of_lists = new ArrayList<>();
        ArrayList<String> selected_types = new ArrayList<>();
        reset_counter();
        int position = 0;
        for (String type: types_array) { list_of_lists.add(function_assignment(type)); }
        for (String[] list: list_of_lists) {
            for (int function = start - 1; function < end; function++) {
                if (list[function].toUpperCase().equals(inputted_function)) {
                    selected_types.add(types_array[position]);
                }
            }
            position++;
        }
        return selected_types;
    }

    // Support functions

    protected void display(ArrayList<String> type_list) {
        boolean display = binary_choice("Display function stack(s)?");
        if (display) {
            System.out.println();
            for (String type: type_list) {
                System.out.println(type + ": " + Arrays.toString(function_assignment(type)));
            }
        }
    }

    protected String[] function_assignment(String type) {
        reset_counter();
        ArrayList<String> possible_top_functions = new ArrayList<>();
        ArrayList<String> possible_bottom_functions = new ArrayList<>();
        if (type.contains("J")) {
            possible_top_functions.addAll(Arrays.asList(judging_c_functions));
            possible_bottom_functions.addAll(Arrays.asList(perceiving_c_functions));
        } else if (type.contains("P")) {
            possible_top_functions.addAll(Arrays.asList(perceiving_c_functions));
            possible_bottom_functions.addAll(Arrays.asList(judging_c_functions));
        }
        String[] cognitive_array = new String[] {"x", "x", "x", "x"};
        char[] type_array = type.toCharArray();
        String[] type_list = new String[4];
        for (char character: type_array) { type_list[counter] = Character.toString(character); counter++;}
        for (int position = 0; position < possible_top_functions.size(); position++) {
            if (possible_top_functions.get(position).contains(type_list[1]) || possible_top_functions.get(position).contains(type_list[2])) {
                //System.out.println(possible_top_functions.get(position) + " and " + possible_bottom_functions.get(position) + " Accepted");
            } else {
                //System.out.println(possible_top_functions.get(position) + " and " + possible_bottom_functions.get(position) + " Failed");
                possible_top_functions.remove(possible_top_functions.get(position));
                possible_bottom_functions.remove(possible_bottom_functions.get(position));
                position -= 1;
            }
        }
        for (int function = 0; function < possible_top_functions.size(); function++) {
            if (possible_top_functions.get(function).contains(type_list[0].toLowerCase())) {
                cognitive_array[0] = possible_top_functions.get(function);
                cognitive_array[3] = possible_bottom_functions.get(function);
            } else {
                cognitive_array[1] = possible_top_functions.get(function);
                cognitive_array[2] = possible_bottom_functions.get(function);
            }
        }
        return cognitive_array;
    }

    protected String function_conversion(String[] cognitive_array) {
        reset_counter();
        String[] temp_array = new String[cognitive_array.length];

        for (String function: cognitive_array) {
            StringBuilder temp = new StringBuilder();
            if (function.toLowerCase().contains("i")) { temp.append("Introverted "); }
            else if (function.toLowerCase().contains("e")) { temp.append("Extroverted "); }
            else { System.out.println("\nNull value entered.");  return null;}

            if (function.contains("N")) { temp.append("Intuition"); }
            else if (function.contains("S")) { temp.append("Sensing"); }
            else if (function.contains("F")) { temp.append("Feeling"); }
            else if (function.contains("T")) { temp.append("Thinking"); }

            temp_array[counter] = temp.toString();
            counter++;
        }
        StringBuilder function_names = new StringBuilder();
        reset_counter();
        for (String function: temp_array) {
            if (temp_array.length <= 1) {
                if (counter < 1) { function_names.append(function); }
                else if (counter >= 1 && counter <= 2) { function_names.append(", ").append(function); }
                else { function_names.append(", and ").append(function).append(". "); }
                counter++;
            } else {
                if (counter < 1) { function_names.append("\nDominant: ").append(function); }
                else if (counter == 1) { function_names.append("\nAuxiliary: ").append(function); }
                else if (counter == 2) { function_names.append("\nTertiary: ").append(function); }
                else if (counter == 3) { function_names.append("\nInferior: ").append(function); }
                counter++;
            }
        }
        return function_names.toString();
    }

    protected void add_function(String title, char letter) {    // function 2
        selected_functions.add(title);
        functions_array_list.add(String.valueOf(letter));
        int position = 4;
        if (letter == 'I' || letter == 'E') {position = 0;}
        if (letter == 'N' || letter == 'S') {position = 1;}
        if (letter == 'T' || letter == 'F') {position = 2;}
        if (letter == 'J' || letter == 'P') {position = 3;}
        try {
            functions_array[position] = letter;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid input. Function " + letter + " does not exist.");
        }
        positions.add(position);
    }

    protected void search(List<String> types_list, char[] searchable_functions, int functions_length) { // function 2
        while (types_list.size() > (16/(Math.pow(2, functions_length))) && counter < 4) {
            char target = searchable_functions[counter];
            if (target != 42) {     // 42 = '*'
                List<String> narrowed_list = new ArrayList<>();
                for (String type: types_list) {
                    if (type.contains(String.valueOf(target))) {
                        narrowed_list.add(type);
                    }
                }
                search_results = narrowed_list;
            }
            counter++;
            search(search_results, searchable_functions, functions_length);
        }
    }

    // Return to root

    protected boolean binary_choice(String message) {
        System.out.println("\n" + message + " y/n: ");
        String answer = input().nextLine().toLowerCase();
        boolean cont;
        if (answer.equals("y")) {
            cont = true;
        }
        else if (answer.equals("n")) {
            cont = false;
        }
        else {
            System.out.println("\nInvalid Input");
            cont = binary_choice(message);
        }
        return cont;
    }

    // To functions

    protected List<String> to_list(String input) {
        List<String> list = new ArrayList<>();
        list.add(input);
        return list;
    }

    protected ArrayList<String> to_array_list(List<String> reg_list) {
        return new ArrayList<>(reg_list);
    }

    // Reset functions

    protected void reset_counter() {
        counter = 0;
    }

    protected void reset_functions_array() {
        Arrays.fill(functions_array, '*');
    }

    protected List<String> reset_types_array_list() {
        return Arrays.asList(types_array);
    }

    protected void reset_search_results() {
        search_results.clear();
    }
}
