package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume " + r.getUuid() + " not exist");
            return;
        }
        storage[index] = r;
    }

    @Override
    public void save(Resume r) {
        if (size >= storage.length) {
            System.out.println("Ошибка: хранилище переполнено");
            return;
        }
        int insertionPoint = findResumeIndex(r.getUuid());
        insertionPoint = -(insertionPoint + 1);
        System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
        storage[insertionPoint] = r;
        size++;
    }

    @Override
    protected int findResumeIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
