package chap09;
import java.util.Comparator;

public class AryLinkedList<E> {


    //노드
    class Node<E> {
        private E data;
        private int next;
        private int dnext;

        //data와 next 설정
        void set(E data, int next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E>[] n;        //리스트 본체
    private int size;
    private int max;
    private int head;
    private int crnt;
    private int deleted;
    private static final int NULL = -1;

    //생성자
    public AryLinkedList(int capacity) {
        head = crnt = max = deleted = NULL;
        try {
            n = new Node[capacity];
            for (int i=0; i<capacity; i++) {
                n[i] = new Node<E>();
            }
            size = capacity;
        } catch (OutOfMemoryError e) {
            size = 0;
        }
    }

    //다음에 삽입하는 record 의 인덱스를 구함
    private int getInsertIndex() {
        if(deleted == NULL) {
            if (max < size) {
                return ++max;
            } else {
                return NULL;
            }
        } else {
            int rec = deleted;
            deleted = n[rec].dnext;
            return rec;
        }
    }

    //record idx를 free 리스트에 등록
    private void deleteIndex(int idx) {
        if (deleted == NULL) {
            deleted = idx;
            n[idx].dnext = NULL;
        } else {
            int rec = deleted;
            deleted = idx;
            n[rec].dnext = rec;
        }
    }

    //노드를 검색
    public E search(E obj, Comparator<? super E> c) {
        int ptr = head;

        while(ptr != NULL) {
            if (c.compare(obj, n[ptr].data) == 0) {
                crnt = ptr;
                return n[ptr].data;
            }
            ptr = n[ptr].next;
        }
        return null;
    }

    //머리에 노드를 삽입
    public void addFirst(E obj) {
        int ptr = head;
        int rec = getInsertIndex();
        if (rec != NULL); {
            head = crnt = rec;
            n[head].set(obj, ptr);
        }
    }

    // 꼬리에 노드를 삽입
    public void addLast(E obj) {
        if (head == NULL) {
            addFirst(obj);
        } else {
            int ptr = head;
            while(n[ptr].next != NULL) {
                ptr = n[ptr].next;
                int rec = getInsertIndex();
                if(rec != NULL) {
                    n[ptr].next = crnt = rec;
                    n[rec].set(obj, NULL);
                }
            }
        }
    }

    //머리 노드를 삭제
    public void removeFirst() {
        if(head != NULL) {
            int ptr = n[head].next;
            deleteIndex(head);
            head = crnt = ptr;
        }
    }


    //꼬리 노드를 삭제
    public void removeLast() {
        if (head != NULL) {
            if(n[head].next == NULL) {
                removeFirst();
            } else {
                int ptr = head;
                int pre = head;

                while(n[ptr].next != NULL) {
                    pre = ptr;
                    ptr = n[ptr].next;
                }
                n[pre].next = NULL;
                deleteIndex(pre);
                crnt = pre;
            }
        }
    }

    //record p를 삭제
    public void remove(int p) {
        if (head != NULL) {
            if (p == head) {
                removeFirst();
            } else {
                int ptr = head;

                while(n[ptr].next != p) {
                    ptr = n[ptr].next;
                    if(ptr == NULL) return;
                }
                n[ptr].next = NULL;
                deleteIndex(ptr);
                n[ptr].next = n[p].next;
                crnt = ptr;
            }
        }
    }

    //선택 노드를 삭제
    public void removeCurrentNode() {
        remove(crnt);
    }

    //모든 노드를 삭제
    public void clear() {
        while (head != NULL) {
            removeFirst();
        }
        crnt = NULL;
    }

    //선택 노드를 하나 뒤쪽으로 이동
    public boolean next() {
        if (crnt == NULL || n[crnt].next == NULL) {
            return false;
        }
        crnt = n[crnt].next;
        return true;
    }

    //선택 노드를 출력
    public void printCurrentNode() {
        if (crnt == NULL) {
            System.out.println("선택 노드가 없습니다.");
        } else {
            System.out.println(n[crnt].data);
        }
    }

    //모든 노드를 출력
    public void dump() {
        int ptr = head;

        while (ptr != NULL) {
            System.out.println(n[ptr].data);
            ptr = n[ptr].next;
        }
    }
}
