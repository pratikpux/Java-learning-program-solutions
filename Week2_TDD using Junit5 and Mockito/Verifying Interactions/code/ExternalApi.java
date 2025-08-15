public interface ExternalApi {
    String getData();
    String getData(String parameter);
    void processData(String data, int count);
    boolean validateData(String data);
}