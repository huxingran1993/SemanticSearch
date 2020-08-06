## Introduction

This application developed based on the 10 tasks below.

Spring Boot + MongoDB + PostMan 

### Key point: 
* Using Lambda Expressions
* Using the stream()
* Learn to **format** code before committing( **_CTRL + SHIFT + L_** )
* Logically analysis problems, take out necessary parameters for each step.


### Task 1: Create relations between words

Create a UI where you can enter:

* a word (W1)
* another word (W2)
* a relation: one of (R) \
** synonym\
** antonym\
** related

- For example:

W1: son      W2: daughter  R: antonym\
W1: road     W2: street    R: synonym\
W1: road     W2: avenue    R: related\
W1: synonym  W2: match     R: related\
W1: antonym  W2: synonym   R: antonym

All three fields need to be non-empty when creating a relation!


== List / Filters

### Task 2: List entries

Each entry is added to a list. The full list of word relations is displayed.

### Task 3: Filter on a type of relation

If I for example select 'related' it would only show me

W1: road     W2: avenue    R: related\
W1: synonym  W2: match     R: related

### Task 4: Checkbox to also show inverse relations

If a word has a certain relation, the inverse where W1 and W2 are swapped has the same relation.
For example (based on sample data at top)

W1: son        W2: daughter  R: antonym\
W1: road       W2: street    R: synonym\
W1: road       W2: avenue    R: related\
W1: synonym    W2: match     R: related\
W1: antonym    W2: synonym   R: antonym\
W1: daughter   W2: son       R: antonym   <- inverse\
W1: street     W2: road      R: synonym   <- inverse\
W1: avenue     W2: road      R: related   <- inverse\
W1: match      W2: synonym   R: related   <- inverse\
W1: synonym    W2: antonym   R: antonym   <- inverse

It would show both original data and inverses!


### Task 5: Lowercase all words

Words are always lowercase on save (without warning!).
Also trim spaces at start and end.

For example: Son => son


== Validations

### Task 6: Limit allowed characters

Only characters from A to Z (both lower and uppercase) and space are allowed.

Operation with illegal characters will fail.


### Task 7: Adding another relation between two word will fail.

If for example\
W1: son W2: daughter  R: antonym

then no other relation between these to words is allowed.
An error is reported to the user.


### ask 8: Inverse relation check

Adding the inverse relation also give an error (and does not add the relation)

If I have
W1: son W2: daughter  R: antonym

then adding this\
W2: daughter W2: son   R: antonym

would **fail**.



== Calculate paths between words

### Task 9: Create UI for path search

You have two fields
* source
* target

and a "Show paths" button

Execute path search

On entering as source "street" and target "avenue" the path would be

   **street ==(synonym)=> road ==(related)=> avenue**

_Note: you will need to also look at derived relations!_


### Task 10: Bonus task

* Checkbox to show transitive relations

For transitive relations these rules apply

if [W1: x, W2: y, R:synonym] and [W1: y, W2: z, R:synonym] then [W1: x, W2: z, R: synonym]\
if [W1: x, W2: y, R:synonym] and [W1: y, W2: z, R:related] then [W1: x, W2: z, R: related]

For example:\
  W1: road     W2: street    R: synonym\
  W1: road     W2: avenue    R: related

results in a new 
  W1: street   W2: avenue    R: related

based on the second rule.


Note: Think about behaviour when both checkboxes for inverse and transitive relations are enabled.
