package com.bookstore.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    public MetricsConfig(MeterRegistry meterRegistry) {
        meterRegistry.gauge("bookstore.books.count", new BookMetrics());
    }

    static class BookMetrics {
        private final BookService bookService;

        public BookMetrics(BookService bookService) {
            this.bookService = bookService;
        }

        public double getBooksCount() {
            return bookService.getAllBooks().size();
        }
    }
}
