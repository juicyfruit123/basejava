package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 1000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public final void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        storage[index] = r;
    }

    public final void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        int resumeIndex = findResumeIndex(r.getUuid());
        if (resumeIndex >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        saveResume(r, resumeIndex);
        size++;
    }

    public final void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int findResumeIndex(String uuid);

    protected abstract void saveResume(Resume r, int index);

    protected abstract void deleteResume(int index);
}
