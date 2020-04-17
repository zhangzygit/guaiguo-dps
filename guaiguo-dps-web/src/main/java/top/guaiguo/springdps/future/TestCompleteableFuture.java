package top.guaiguo.springdps.future;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestCompleteableFuture {

    final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    /**
     * 转换
     */
    @Test
    public void thenApply() {
        String result = CompletableFuture.supplyAsync(() -> "hello").thenApply(s -> s + " world").join();
        System.out.println(result);
    }

    /**
     * 消费
     */
    @Test
    public void thenAccept() {
        CompletableFuture.supplyAsync(() -> {
            sleep(2);
            return "hello";
        }).thenAccept(s -> System.out.println(s + " world"));
        sleep(3);
    }

    @Test
    @SneakyThrows
    public void thenRun() {
        CompletableFuture.supplyAsync(() -> {
            sleep(2);
            return "hello";
        }).thenRun(() -> System.out.println("hello world"));
        sleep(3);
    }

    @Test
    public void thenCombine() {
        System.out.println(Thread.currentThread().getName());
        System.out.println(executorService.getPoolSize());
        ArrayList<String> join = CompletableFuture.supplyAsync(() -> {
            sleep(2);
            System.out.println(executorService.getPoolSize());
            System.out.println(Thread.currentThread().getName());
            return "hello ";
        }, executorService).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            sleep(3);
            System.out.println(executorService.getPoolSize());
            System.out.println(Thread.currentThread().getName());
            return "world";
        }, executorService), (s, v) -> {
            System.out.println(executorService.getPoolSize());
            System.out.println(Thread.currentThread().getName());
            return Lists.newArrayList(s, v);
        }, executorService).join();
        System.out.println(join);
    }

    @Test
    public void thenAcceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            sleep(2);
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            sleep(2);
            return " world";
        }), (s, v) -> System.out.println(s + v));
        sleep(5);
    }

    @Test
    public void runAfterBoth() {
        CompletableFuture.supplyAsync(() -> {
            sleep(2);
            return "hello ";
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            sleep(2);
            return "world";
        }), () -> System.out.println("both run over"));
        sleep(5);
    }

    @Test
    public void applyToEither() {
        Integer join = CompletableFuture.supplyAsync(() -> {
            sleep(1);
            return 1;
        }).applyToEitherAsync(CompletableFuture.supplyAsync(() -> {
            sleep(2);
            return 2;
        }), s -> s).join();
        System.out.println(join);
    }

    @Test
    public void acceptToEither() {
        CompletableFuture.supplyAsync(() -> {
            sleep(1);
            return 1;
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            sleep(2);
            return 2;
        }), System.out::println);
        sleep(3);
    }

    @Test
    public void runAfterEither() {
        CompletableFuture.supplyAsync(() -> {
            sleep(1);
            return 1;
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            sleep(2);
            return 2;
        }), () -> System.out.println(3));
        sleep(3);
    }

    @Test
    public void whenComplete() {
        String result = CompletableFuture.supplyAsync(() -> {
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).whenComplete((s, t) -> {
            System.out.println(s);
            System.out.println(t.getMessage());
        }).exceptionally(s -> {
            System.out.println(s.getMessage());
            return "excep";
        }).handle((s, t) -> {
            if (t != null) {
                return "handle";
            }
            return s;
        }).join();
        System.out.println(result);
    }

    @Test
    public void handle() {
        String result = CompletableFuture.supplyAsync(() -> {
            sleep(3);
            //出现异常
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).handle((s, t) -> {
            if (t != null) {
                return "hello world";
            }
            return s;
        }).join();
        System.out.println(result);
    }


    @Test
    public void exceptionally() {
        String result = CompletableFuture.supplyAsync(() -> {
            sleep(1);
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "hello world";
        }).join();
        System.out.println(result);
    }

    @Test
    public void testAnyOf() {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            sleep(new Random().nextInt(4));
            return 1;
        });
        CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            sleep(new Random().nextInt(4));
            return 2;
        });
        Object join = CompletableFuture.anyOf(integerCompletableFuture, integerCompletableFuture1).join();
        System.out.println(join);

    }

    @Test
    public void testAllOf() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            sleep(new Random().nextInt(4));
            return 1;
        });
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(new Random().nextInt(4));
            return 2;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            sleep(new Random().nextInt(4));
            return null;
        });
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            sleep(new Random().nextInt(4));
            return 4;
        });

        List<CompletableFuture<Integer>> futures = Lists.newArrayList(future, future1, future2, future3);
        List<Integer> join = sequence(futures)
                .handle((s, t) -> {
                    if (t != null) {
                        System.out.println(t.getMessage());
                        return null;
                    }
                    return s;
                }).join();
        System.out.println(join);


    }

    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.<T>toList()));
    }

    private void sleep(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
