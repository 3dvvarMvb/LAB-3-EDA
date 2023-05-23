public class MinHeap {
    private int capacity;
    private Video[] pq;
    private int size;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.pq = new Video[capacity];
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public Video getMin() {
        if (size == 0) {
            throw new IllegalStateException("La lista está vacía");
        } else {
            return pq[0];
        }
    }

    private int compare(Video v1, Video v2) {
        if (v1.getPopularity() == v2.getPopularity()) {
            return v1.getVideoTitle().compareTo(v2.getVideoTitle());
        } else {
            return Float.compare(v1.getPopularity(), v2.getPopularity());
        }
    }

    private void swap(int v1, int v2) {
        Video temp = pq[v1];
        pq[v1] = pq[v2];
        pq[v2] = temp;
    }

    private void swim(int k) {
        int parent;
        if(k%2==0){
            parent=(k/2)-1;
        }
        else{
            parent=(k/2);
        }
        if (k>0 && compare(pq[parent], pq[k]) > 0) {
            swap(k, parent);
            swim(parent);
        }
    }

    public void insert(Video video) {
        if (size == capacity) {
            throw new IllegalStateException("La lista está llena");
        }
        pq[size] = video;
        swim(size);
        size++;
    }

    private void sink(int k) {
        int left = 2*k;
        int right = 2*k+1;
        int smallest = k;
        if (left < size && compare(pq[left], pq[smallest]) > 0) {
            smallest = left;
        }
        if (right < size && compare(pq[right], pq[smallest]) > 0) {
            smallest = right;
        }
        if (smallest != k) {
            swap(k, smallest);
            sink(smallest);
        }
    }

    public Video deleteMin(){
        if(size == 0){
            throw new IllegalStateException("La lista está vacía");
        }
        int minVideo =0;
        for(int i=0;i<size;i++){
            if(pq[i].getPopularity()<pq[minVideo].getPopularity()){
                minVideo=i;
            }
        }
        Video Min=pq[minVideo];
        swap(minVideo,--size);
        pq[size]=null;
        sink(minVideo);
        return Min;
    }

    public Video delete(){
        if(size == 0){
            throw new IllegalStateException("La lista está vacía");
        }
        Video video = pq[0];
        pq[0] = pq[--size];
        pq[size] = null;
        sink(0);
        return video;
    }

    public Video getVideo(int k) {
        if (k < size && k >= 0) {
            return pq[k];
        } else {
            return null;
        }
    }

    public void printPriorityQueue() {
        for (int i = 0; i < size; i++) {
            if (pq[i] != null) {
                System.out.printf("%s ", getVideo(i).getVideoTitle());
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Test cases
        MinHeap pq = new MinHeap(50);
        pq.insert(new Video("1", "video 1", "1", "channel title", "12-12-1221", 131, 323, 323, 22f));
        pq.insert(new Video("2", "video 2", "1", "channel title", "12-12-1221", 131, 323, 323, 12f));
        pq.insert(new Video("3", "video 3", "1", "channel title", "12-12-1221", 131, 323, 323, 13f));
        pq.insert(new Video("4", "video 4", "1", "channel title", "12-12-1221", 131, 323, 323, 14f));
        pq.insert(new Video("5", "video 5", "1", "channel title", "12-12-1221", 131, 323, 323, 15f));
        pq.insert(new Video("6", "video 6", "1", "channel title", "12-12-1221", 131, 323, 323, 16f));
        pq.insert(new Video("7", "video 7", "1", "channel title", "12-12-1221", 131, 323, 323, 17f));
        pq.insert(new Video("8", "video 8", "1", "channel title", "12-12-1221", 131, 323, 323, 18f));
        pq.insert(new Video("9", "video 9", "1", "channel title", "12-12-1221", 131, 323, 323, 19f));
        pq.insert(new Video("10", "video 10", "1", "channel title", "12-12-1221", 131, 323, 323, 20f));
        pq.insert(new Video("11", "video 11", "1", "channel title", "12-12-1221", 131, 323, 323, 21f));
        pq.insert(new Video("12", "video 12", "1", "channel title", "12-12-1221", 131, 323, 323, 11f));

        pq.printPriorityQueue();
        pq.deleteMin().reproduce();
        pq.printPriorityQueue();
    }
}
