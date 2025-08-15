public class MyService {
    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public String fetchDataWithParameter(String param) {
        return externalApi.getData(param);
    }

    public void processInformation(String info) {
        externalApi.processData(info, 5);
        externalApi.validateData(info);
    }

    public String fetchMultipleData() {
        String data1 = externalApi.getData("first");
        String data2 = externalApi.getData("second");
        return data1 + data2;
    }
}
