package ru.ssau.worddist.model;

import ru.ssau.worddist.view.DistUI;

import java.util.*;

public enum OperatorEnum {
    OPERATOR_ADDITION, OPERATOR_REMOVE, OPERATOR_CHECK, OPERATOR_UNKNOWN;

    public static final String ERROR_UNKNOWN_OPERATOR = "Error! Unexpected operator!";

    /**
     * Выпополняет одно действие из OperatorEnum
     *
     * @param str_state - строка букв, хранящее состояние
     * @param str       - строка, которую мы добавляем/удаляем/проверяем посимвольно!
     * @return
     */
    public String process(String str_state, String str) {
        //строку запихиваем в set
        HashMap<Character, Integer> map_state = new HashMap<Character, Integer>();
        for (int i = 0; i < str_state.length(); i++) {
            char c = str_state.charAt(i);
            if (map_state.containsKey(c)) {
                int count_character = map_state.get(c);
                map_state.put(c, count_character + 1);
            } else {
                map_state.put(c, 1);
            }
        }
        switch (this) {
            case OPERATOR_ADDITION:
                for (int i = 0; i < str.length(); i++) {
                    //добавление i ого символа из строки в множество set
                    char c = str.charAt(i);
                    if (map_state.containsKey(c)) {
                        int count_character = map_state.get(c);
                        map_state.put(c, count_character + 1);
                    } else {
                        map_state.put(c, 1);
                    }
                }
                //далее, и до конца - переводим map в строку
                ArrayList<Character> list_add = new ArrayList<>();
                for (Map.Entry<Character, Integer> entry : map_state.entrySet()) {
                    for (int i = 0; i < entry.getValue(); i++) {
                        list_add.add(entry.getKey());
                    }
                }
                //ниже переводим ArrayList в массив char[] и затем в последней строке в String
                char[] tmp_add = new char[list_add.size()];
                int index_add = 0;
                for (char c : list_add){
                    tmp_add[index_add++] = c;
                }
                return new String(tmp_add);

            case OPERATOR_REMOVE:
                for (int i = 0; i < str.length(); i++) {
                    //добавление i ого символа из строки в множество set
                    char c = str.charAt(i);
                    if (map_state.containsKey(c)) {
                        int count_character = map_state.get(c);
                        map_state.put(c, count_character - 1);
                        if (count_character < 1){
                            map_state.remove(c);
                        }
                    } else {
                        map_state.put(c, 1);
                    }
                }
                //далее, и до конца - переводим map в строку
                ArrayList<Character> list_rem = new ArrayList<>();
                for (Map.Entry<Character, Integer> entry : map_state.entrySet()) {
                    for (int i = 0; i < entry.getValue(); i++) {
                        list_rem.add(entry.getKey());
                    }
                }
                //ниже переводим ArrayList в массив char[] и зачем в последней строке в String
                char[] tmp_rem = new char[list_rem.size()];
                int index_rem = 0;
                for (char c : list_rem){
                    tmp_rem[index_rem++] = c;
                }
                return new String(tmp_rem);

            case OPERATOR_CHECK:
                HashMap<Character, Integer> map_user = new HashMap<Character, Integer>();
                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);
                    if (map_user.containsKey(c)) {
                        int count_character = map_user.get(c);
                        map_user.put(c, count_character + 1);
                    } else {
                        map_user.put(c, 1);
                    }
                }
                //сравнивая мапы, понимаем, можно ли составить слово (ниже и далее)
                Boolean flag = true; // если всё ок
                for (Map.Entry<Character, Integer> entry : map_user.entrySet()) {
                    char key = entry.getKey();
                    int value = entry.getValue();
                    if (!map_state.containsKey(key)){
                        flag = false;
                        break;
                    }
                    if (map_state.get(key) < map_user.get(key)){
                        flag = false;
                        break;
                    }
                }
                if (flag) { // если проверка прошла гладко
                    return DistUI.MESSAGE_SUCCESS;
                } else {
                    return DistUI.MESSAGE_FAIL;
                }
        }
        throw new RuntimeException(ERROR_UNKNOWN_OPERATOR);
    }
}