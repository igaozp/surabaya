module surabaya.feature.main {
    uses language.spi.Search;
    requires jdk.incubator.concurrent;
    requires java.net.http;
    requires java.desktop;
}