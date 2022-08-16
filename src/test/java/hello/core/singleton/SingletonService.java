package hello.core.singleton;

public class SingletonService {

    //1. 자기 자신을 private static으로 딱 1개만 선언(자바가 올라가면서 미리 생성되는 형태)
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어둬서 getInstance 메서드를 통해서만 오직 인스턴스 조회 가능(항상 같은 인스턴스 반환)
    public static SingletonService getInstance() {
        return instance;
    }

    //3. private 생성자를 선언함으로써 외부에서 new 키워드를 통해 객체 생성 방지
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
