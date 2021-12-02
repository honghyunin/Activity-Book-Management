package com.CRUD.test.domain.book.dto;

import com.CRUD.test.domain.book.Book;
import lombok.Getter;
import lombok.Setter;

public class BookRequestDto {

    @Getter @Setter
    public static class Add {
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

    public static class Delete {

    }

    public static class Renew {

    }

    public static class Overdue {

    }

    public static class LookFor {

    }
}

