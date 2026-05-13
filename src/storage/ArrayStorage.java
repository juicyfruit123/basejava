package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size >= storage.length) {
            System.out.println("Ошибка: хранилище переполнено");
            return;
        }
        if (findResumeIndex(r.getUuid()) != -1) {
            System.out.println("Resume " + r.getUuid() + " exist in storage");
            return;
        }
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Resume " + r.getUuid() + " not exist");
            return;
        }
        storage[index] = r;
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return;
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}