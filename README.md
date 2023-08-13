# What is this repo?

This is just a simple project for me to study Spring Boot.

I decided to do this project, getting stock prices from third-party providers because is
something I like. Is less likely to get bored with this pet project because of that.
This project doesn't aim to compete with Bloomberg (not yet, at least) or any other quant trading company.

## What is the stack used?

As I mentioned above, this project was made with Spring Boot, a Java Framework.

To read some csv, I used the opencsv, and to connect with the API I used the webflux (probably not in the best
way, I'm afraid - I still need to study it more).

## What is left to be done?

A lot of things, really.

A non-exhaustive list includes:

- [x] Make the "sync" route async (using RabbitMQ or 0MQ, or something else): I choose RabbitMQ :)
- [ ] Aggregate data. For example, get the highest and lowest prices in a date range.
- [ ] Create a proper docker for this project.
- [ ] Create a front-end to consume this API.
- [ ] Make those routes protected using Spring Security.
- [ ] Create more tests.
- [x] Make list request to be pageable.
