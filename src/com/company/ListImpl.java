package com.company;



import java.util.ArrayList;
import java.util.Arrays;

public class ListImpl implements ListInterface{

    private String[] arrayStore;
    private static final int arrayCapacity = 10;
    private int size;

    public ListImpl() {
        this.arrayStore = new String[arrayCapacity];
    }

    public void checker(String value){
        if (value == null){
            throw new ValueNullException("can't be null");
        }
    }
    private void increaseCapacity() {
        int increased = arrayStore.length * 2;
        arrayStore = Arrays.copyOf(arrayStore, increased);
    }

    private void sortArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[j].compareTo(array[i]) < 0) {
                    String buffer = array[i];
                    array[i] = array[j];
                    array[j] = buffer;
                }
            }
        }
    }

    @Override
    public String add(String item) {
        checker(item);
        if(size == arrayCapacity){
            increaseCapacity();
        }
        arrayStore[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        checker(item);
        if(index < 0 || index > size - 1){
            throw new ArrayIndexOutOfBoundsException("out of size array");
        }
        increaseCapacity();
        System.arraycopy(arrayStore, index, arrayStore, index+1, size - index);
        arrayStore[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checker(item);
        if(index < 0 || index > size - 1){
            throw new ArrayIndexOutOfBoundsException("out of size array");
        }
        arrayStore[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        checker(item);
        int indexForRemoveItem = indexOf(item);
        if(indexForRemoveItem == -1){
            throw new SearchReturnedNoMatchesException("There are no matches");
        }
        System.arraycopy(arrayStore, indexForRemoveItem + 1, arrayStore, indexForRemoveItem, size - indexForRemoveItem);
        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("index wrong input");
        }
        System.arraycopy(arrayStore, index + 1, arrayStore, index, size - index);
        size--;
        return arrayStore[index];
    }

    @Override
    public boolean contains(String item) {
        checker(item);
        boolean result = false;
        for (String s : arrayStore) {
            if (s.compareTo(item) == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public int indexOf(String item) {
        checker(item);
        for (String s : arrayStore) {
            if(s.equals(item)){
                return 1;
            } else throw new SearchReturnedNoMatchesException("There are no matches");
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checker(item);
        for (int i = size - 1; i >= 0; i--) {
            if (arrayStore[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checker(arrayStore[index]);
        if (index > size){
            throw new IndexOutOfBoundsException("wrong index");
        }
        return arrayStore[index];
    }

    @Override
    public boolean equals(ArrayList otherList) {
        if (otherList == null || size != otherList.size()) {
            return false;
        }
        String[] bufferArray = new String[otherList.size()];
        for (int i = 0; i < otherList.size(); i++) bufferArray[i] = otherList.get(i).toString();
        sortArray(bufferArray);
        sortArray(arrayStore);
        for (int i = 0; i <= size ; i++) {
            if (get(i).equals(otherList.get(i))) {
                return false;
            }
        } return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        arrayStore = new String[arrayCapacity];
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(arrayStore, size);
    }
}
