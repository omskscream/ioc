package io.github.omskscream.ioc;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

/**
 * @author omskscream
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 3, jvmArgsAppend = {"-Xmx512m", "-server", "-XX:+AggressiveOpts"})
@Measurement(iterations = 10, time = 3, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 20, time = 3, timeUnit = TimeUnit.SECONDS)
public class ContainerBenchmark {

    @Benchmark
    public void testContainer() {

    }
}
