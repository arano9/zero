package service;

/**
 * @author arano
 * @since 2021/6/10 10:10
 */
public interface HelloService {
    String hello(String name);

    T echo(T param);

    class T {
        String a;
        String b;

        @Override
        public String toString() {
            return "T{" +
                    "a='" + a + '\'' +
                    ", b='" + b + '\'' +
                    '}';
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }
}
