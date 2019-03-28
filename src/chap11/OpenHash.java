package chap11;

//오픈 주소법에 의한 해시

public class OpenHash<K,V> {
    //버킷의 상태
    enum Status {OCCUPIED, EMPTY, DELETED}; //{데이터저장, 비어있음, 삭제 마침}

    //버킷
    static class Bucket<K,V> {
        private K key;
        private V data;
        private Status stat;

        //생성자
        Bucket() {
            stat = Status.EMPTY;        //버킷 비어있음
        }

        //모든 필드에 값을 설정
        void set(K key, V data, Status stat) {
            this.key = key;
            this.data = data;
            this.stat = stat;
        }

        //상태 설정
        void setStat(Status stat) {
            this.stat = stat;
        }

        //키 값 반환
        K getKey() {
            return key;
        }

        //데이터 반환
        V getValue() {
            return data;
        }

        //키의 해시값 반환
        public int hashCode() {
            return key.hashCode();
        }
    }

    private int size;
    private Bucket<K,V>[] table;

    public OpenHash(int size) {
        try {
            table = new Bucket[size];
            for (int i = 0; i < size; i++) {
                table[i] = new Bucket<K, V>();
            }
            this.size = size;
        } catch (OutOfMemoryError e) {
            this.size = 0;
        }
    }

    //해시 값을 구함
    public int hashValue(Object key) {
        return key.hashCode() % size;
    }

    //재해시 값을 구함
    public int rehashValue(int hash) {
        return (hash + 1) % size;
    }

    //키 값 key를 갖는 버킷의 검색
    private Bucket<K,V> searchNode(K key) {
        int hash = hashValue(key);
        Bucket<K,V> p = table[hash];

        for (int i=0; p.stat != Status.EMPTY && i < size; i++) {
            if (p.stat == Status.OCCUPIED && p.getKey().equals(key) ) {
                return p;
            }
            hash = rehashValue(hash);
            p=table[hash];
        }
        return null;
    }

    //키 값 key를 갖는 요소의 검색(데이터 반환)
    public V search(K key) {
        Bucket<K,V> p = searchNode(key);      //선택 노드

        if(p != null) {
            return p.getValue();
        } else {
            return null;
        }
    }

    //키 값 key, 데이터 data를 갖는 요소의 추가
    public int add(K key, V data){
        if (search(key) != null) {
            return 1;
        }

        int hash =hashValue(key);
        Bucket<K,V> p = table[hash];
        for (int i =0; i<size; i++) {
            if (p.stat == Status.EMPTY || p.stat == Status.DELETED) {
                p.set(key, data, Status.OCCUPIED);
                return 0;
            }
            hash = rehashValue(hash);    //재해시
            p = table[hash];
        }
        return 2;
    }

    //키 값 key를 갖는 요소의 삭제
    public int remove(K key) {
        Bucket<K,V> p = searchNode(key);
        if (p==null) {
            return 1;
        }
        p.setStat(Status.DELETED);
        return 0;
    }

    //해시 테이블을 덤프
    public void dump() {
        for (int i=0; i<size; i++) {
            System.out.printf("%02d ", i);
            switch(table[i].stat) {
                case OCCUPIED:
                    System.out.printf("%s (%s)\n", table[i].getKey(), table[i].getValue());
                    break;

                case EMPTY:
                    System.out.printf("-- 미등록 --");
                    break;

                case DELETED:
                    System.out.printf("-- 삭제 마침 --");
                    break;
            }
        }
    }
}
