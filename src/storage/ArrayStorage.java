package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
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

    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Resume " + r.getUuid() + " not exist");
            return;
        }
        storage[index] = r;
    }

    protected int findResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}