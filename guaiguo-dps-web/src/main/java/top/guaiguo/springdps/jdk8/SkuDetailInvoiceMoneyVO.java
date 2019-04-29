package top.guaiguo.springdps.jdk8;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2018-05-04 15:06
 */
public class SkuDetailInvoiceMoneyVO {

    private Long skuId;
    private String skuName;
    private Long orderId;
    private BigDecimal salePrice;
    private BigDecimal paidAmount;
    private Integer skuNum;
    private String saleTaxCodeValue;
    private Long orderDetailId;

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Integer getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(Integer skuNum) {
        this.skuNum = skuNum;
    }

    public String getSaleTaxCodeValue() {
        return saleTaxCodeValue;
    }

    public void setSaleTaxCodeValue(String saleTaxCodeValue) {
        this.saleTaxCodeValue = saleTaxCodeValue;
    }

    @Override
    public String toString() {
        return "SkuDetailInvoiceMoneyVO{" +
                "skuId=" + skuId +
                ", skuName='" + skuName + '\'' +
                ", orderId=" + orderId +
                ", salePrice=" + salePrice +
                ", paidAmount=" + paidAmount +
                ", skuNum=" + skuNum +
                ", saleTaxCodeValue='" + saleTaxCodeValue + '\'' +
                '}';
    }

    public static void main(String[] args) {
        List<SkuDetailInvoiceMoneyVO> list = new ArrayList<>();
        SkuDetailInvoiceMoneyVO s = new SkuDetailInvoiceMoneyVO();
        s.setSkuId(1213L);
        s.setSkuName("de");
        SkuDetailInvoiceMoneyVO vo = new SkuDetailInvoiceMoneyVO();
        vo.setSkuId(231L);
        vo.setSkuName("ha");
        SkuDetailInvoiceMoneyVO v1o = new SkuDetailInvoiceMoneyVO();
        v1o.setSkuId(2234L);
        v1o.setSkuName("ha");
        list.add(s);
        list.add(vo);
        list.add(v1o);

        //按照SKUID升序，名称倒叙排列
        List<SkuDetailInvoiceMoneyVO> collect = list.stream()
                .sorted(Comparator.comparing(SkuDetailInvoiceMoneyVO::getSkuId).thenComparing(SkuDetailInvoiceMoneyVO
                        ::getSkuName).reversed()).collect(Collectors.toList());

        Comparator<SkuDetailInvoiceMoneyVO> comparing = Comparator.comparing(SkuDetailInvoiceMoneyVO::getSkuId);
        comparing.reversed();

        Collections.sort(list, new Comparator<SkuDetailInvoiceMoneyVO>() {
            @Override
            public int compare(SkuDetailInvoiceMoneyVO o1, SkuDetailInvoiceMoneyVO o2) {
                return o1.getSkuId().compareTo(o2.getSkuId());
            }
        });

        Function<SkuDetailInvoiceMoneyVO, Long> getSkuId = SkuDetailInvoiceMoneyVO::getSkuId;
        Collector<Object, ?, List<Object>> objectListCollector = Collectors.toList();

        List<Long> ha = list.parallelStream()
                .filter(t -> t.getSkuName().equals("ha"))
                .sorted(Comparator.comparing(SkuDetailInvoiceMoneyVO::getSkuId).reversed())
                .map(SkuDetailInvoiceMoneyVO::getSkuId)
                .collect(Collectors.toList());
        System.out.println(ha.toString());
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        nums.stream()
                .map(n -> n * n)
                .forEach(System.out::println);

        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] integers = Arrays.stream(sixNums).filter(s2 -> s2 % 2 == 0).toArray(Integer[]::new);

        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());

        System.out.println("--------------------------------------");
        String[] strings = Stream.of("one", "two", "three", "four")
                .filter(str -> str.length() > 3)
                .peek(System.out::println)
                .map(String::toUpperCase)
                .peek(System.out::println)
                .toArray(String[]::new);

        System.out.println(Arrays.toString(strings));

        System.out.println("--------------------------------------");
        String strr = "123123";
        Optional.ofNullable(strr).ifPresent(System.out::println);
        Integer integer = Optional.ofNullable(strr).map(String::length).orElse(-1);

        System.out.println("--------------------------------------");
        Stream.of("one1", "two", "three", "four")
                .min(Comparator.comparing(String::length))
                .ifPresent(System.out::println);

        System.out.println("--------------------------------------");
        Stream.of("one1", "two", "three", "four")
                .limit(2)
                .reduce(String::concat)
                .ifPresent(System.out::println);

        Random seed = new Random();
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random);

        Instant instant = Instant.now();
        System.out.println(instant);
        LocalDate now = LocalDate.now();
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTimeFormatter.format(now1);

        System.out.println(format);
    }
}
