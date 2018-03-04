package studentCoursePlanner.state;

public class Category {
    public enum LongProgramming{
        A,B,C,D
    }
    public enum DsAlgo{
        E,F,G,H
    }
    public enum Hardware{
        I,J,K,L
    }
    public enum DataAnalytics {
        M,N,O,P
    }
    public enum Elective{
        Q,R,S,T,U,V,W,X,Y,Z
    }

    @Override
    public String toString() {
        return "Category{}";
    }
}
