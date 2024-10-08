package hello.core.singleton;

// 테스트 케이스가 아니라 main 에 영향 끼치지않고 테스트 하기위해 test 폴더 하위로 생성
public class SingletonService {

    // static 레벨로 갖고있기 때문에 딱 1개만 존재하게 된다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
    }
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
