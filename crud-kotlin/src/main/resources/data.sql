-- User ADMIN
INSERT INTO users (name, age, document, email, password, role)
VALUES ('Admin User', 35, '11111111111', 'admin@email.com', '$2a$10$7s0UxWqRS3nkTrylgU4QC.k3OZKhKN/fC.AN6D/ZRD8MtWNv.y1Vy', 'ADMIN');

-- Users SIMPLE
INSERT INTO users ( name, age, document, email, password, role)
VALUES ('João Silva', 28, '22222222222', 'joao@email.com', '$2a$10$7s0UxWqRS3nkTrylgU4QC.k3OZKhKN/fC.AN6D/ZRD8MtWNv.y1Vy', 'SIMPLE');

INSERT INTO users (name, age, document, email, password, role)
VALUES ('Maria Santos', 32, '33333333333', 'maria@email.com', '$2a$10$7s0UxWqRS3nkTrylgU4QC.k3OZKhKN/fC.AN6D/ZRD8MtWNv.y1Vy', 'SIMPLE');

-- Endereços
INSERT INTO address ( dress_code, street, number, complement, city, state, country, user_id)
VALUES ( '12345-678', 'Rua Admin', 100, 'Apto 1', 'São Paulo', 'SP', 'Brasil', 1);

INSERT INTO address ( dress_code, street, number, complement, city, state, country, user_id)
VALUES ( '23456-789', 'Rua João', 200, 'Casa', 'Rio de Janeiro', 'RJ', 'Brasil', 2);

INSERT INTO address ( dress_code, street, number, complement, city, state, country, user_id)
VALUES ( '34567-890', 'Rua Maria', 300, 'Apto 5', 'Belo Horizonte', 'MG', 'Brasil', 3);

-- 15 Livros para Admin
INSERT INTO books (title, author, isbn, publisher, published_year, description, pages, language, genre, user_id)
VALUES ('Clean Code', 'Robert Martin', '978-0132350884', 'Prentice Hall', 2008, 'Guia de boas práticas', 464, 'EN', 'Técnico', 1);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Domain-Driven Design', 'Eric Evans', '978-0321125217', 'Addison-Wesley', 2003, 400, 'EN', 'Técnico', 1);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Refactoring', 'Martin Fowler', '978-0201485677', 'Addison-Wesley', 1999, 431, 'EN', 'Técnico', 1);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Design Patterns', 'Gang of Four', '978-0201633610', 'Addison-Wesley', 1994, 395, 'EN', 'Técnico', 1);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('The Pragmatic Programmer', 'Hunt & Thomas', '978-0135957059', 'Addison-Wesley', 2019, 352, 'EN', 'Técnico', 1);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Kotlin in Action', 'Dmitry Jemerov', '978-1617293290', 'Manning', 2017, 360, 'EN', 'Técnico', 1);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Spring Boot in Action', 'Craig Walls', '978-1617292545', 'Manning', 2016, 264, 'EN', 'Técnico', 1);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Effective Java', 'Joshua Bloch', '978-0134685991', 'Addison-Wesley', 2018, 416, 'EN', 'Técnico', 1);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Head First Design Patterns', 'Freeman et al', '978-0596007126', 'O Reilly', 2004, 694, 'EN', 'Técnico', 1);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Microservices Patterns', 'Chris Richardson', '978-1617294549', 'Manning', 2018, 520, 'EN', 'Técnico', 1);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Building Microservices', 'Sam Newman', '978-1492034025', 'O Reilly', 2021, 612, 'EN', 'Técnico', 1);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Test Driven Development', 'Kent Beck', '978-0321146533', 'Addison-Wesley', 2002, 240, 'EN', 'Técnico', 1);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Working Effectively with Legacy Code', 'Michael Feathers', '978-0131177055', 'Prentice Hall', 2004, 464, 'EN', 'Técnico', 1);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Continuous Delivery', 'Jez Humble', '978-0321601919', 'Addison-Wesley', 2010, 512, 'EN', 'Técnico', 1);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('The DevOps Handbook', 'Gene Kim', '978-1942788003', 'IT Revolution', 2016, 480, 'EN', 'Técnico', 1);

-- 10 Livros para João
INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('1984', 'George Orwell', '978-0451524935', 'Signet Classic', 1949, 328, 'EN', 'Ficção', 2);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('O Senhor dos Anéis', 'J.R.R. Tolkien', '978-8533613379', 'Martins Fontes', 1954, 1200, 'PT', 'Fantasia', 2);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', '978-8532530787', 'Rocco', 1997, 264, 'PT', 'Fantasia', 2);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('O Hobbit', 'J.R.R. Tolkien', '978-8533619357', 'Martins Fontes', 1937, 336, 'PT', 'Fantasia', 2);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('A Revolução dos Bichos', 'George Orwell', '978-8535909555', 'Companhia das Letras', 1945, 152, 'PT', 'Ficção', 2);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Dom Casmurro', 'Machado de Assis', '978-8572326971', 'Ática', 1899, 256, 'PT', 'Romance', 2);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Cem Anos de Solidão', 'Gabriel García Márquez', '978-8501012371', 'Record', 1967, 424, 'PT', 'Romance', 2);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', '978-8522008735', 'Agir', 1943, 96, 'PT', 'Infantil', 2);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('O Cortiço', 'Aluísio Azevedo', '978-8508040537', 'Ática', 1890, 272, 'PT', 'Romance', 2);

INSERT INTO books (title, author, isbn, publisher, published_year, pages, language, genre, user_id)
VALUES ('Memórias Póstumas de Brás Cubas', 'Machado de Assis', '978-8535911664', 'Penguin', 1881, 368, 'PT', 'Romance', 2);