package storage;

import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index < 1) {
            System.out.println("Resume " + r.getUuid() + " not exist");
            return;
        }
        storage[index] = r;
    }

    public void save(Resume r) {
        if (size >= storage.length) {
            System.out.println("Ошибка: хранилище переполнено");
            return;
        }
        int resumeIndex = findResumeIndex(r.getUuid());
        if (resumeIndex > 0) {
            System.out.println("Resume " + r.getUuid() + " exist in storage");
            return;
        }
        saveResume(r, resumeIndex);
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return;
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int findResumeIndex(String uuid);

    protected abstract void saveResume(Resume r, int index);
}
