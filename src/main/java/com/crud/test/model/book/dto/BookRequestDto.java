package com.crud.test.model.book.dto;

import com.crud.test.model.book.Book;
import lombok.Getter;
import lombok.Setter;

public class BookRequestDto {

    @Getter @Setter
    public static class Add {
        private Long idx;
        private String title;
        private String Author;
        private String category;
        private boolean rentalAbleBook = true;
        public Book toEntity(){
            return Book.builder()
                    .title(title)
                    .author(Author)
                    .category(category)
                    .rentalAbleBook(rentalAbleBook)
                    .build();
        }
    }
    @Getter @Setter
    public static class Delete {
        private Long idx;
    }

    @Getter @Setter
    public static class findBy {
        private String findBook;
    }
}

