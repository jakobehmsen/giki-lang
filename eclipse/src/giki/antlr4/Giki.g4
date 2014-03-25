grammar Giki;

program: programElements;
programElements: (module | expressions)*;
module: KEYWORD_DEFINE moduleModifiers selector=ID 
    (
        /*COLON expression SEMICOLON
        |*/
        OPEN_PARENTHESIS programElements CLOSE_PARENTHESIS
    )
;
moduleModifiers: ID*;
expressions: expression (SEMICOLON expression?)*;
expression: variableDeclaration;
variableDeclaration: 
    KEYWORD_VAR isDeclaration=ID (hasInitialization=EQUALS value=decision)? 
    | 
    assignment;
assignment: id=ID isAssignment=EQUALS value=decision | decision;
decision: value=pipeline decisionTail;
decisionTail: (PIPE pipeline)*;
pipeline: 
    lhs=preFixOperation 
    (
        (hasFrom=pipelineFrom)?
        (hasInters=pipelineInter)*
        (hasTo=pipelineTo)?
    );
pipelineFrom:
    PIPELINE_FROM body=preFixOperation;
pipelineInter:
    PIPELINE preFixOperation;
pipelineTo:
    PIPELINE_TO body=preFixOperation;
preFixOperation: 
    preFixRest | preFixFile | compose;
preFixRest: KEYWORD_REST expr=preFixOperation;
preFixFile: KEYWORD_FILE expr=preFixOperation;
compose: lhs=terms (isCompose=HASH terms)*;
// Negative composition?

terms: term+;
term:
    isNot=EXCLAMATION?
    termValue 
    (
        lookupChain?
        (isMulti=ASTERIX)?
    )
    ;
termValue: 
    (interval | ioOperation | identifier | group | list | map | 
    produceVerbatim | quote | interpolation);
lookupChain: (DOT ID)+;
interval: lhs=produceString DOUBLE_DOT rhs=produceString;
slotSet: ID COLON value=expression;
group: OPEN_PARENTHESIS expressions CLOSE_PARENTHESIS;
list: OPEN_SQ_BRACKET expressions? CLOSE_SQ_BRACKET;
map: OPEN_BRACKET (slotSet (COMMA slotSet)*)? CLOSE_BRACKET;
ioOperation: 
    (
        consumeString | produceString | consumeInteger | produceInteger 
        | namedPrimitive | move | peek | next
    )
    IO_SCOPE?
    ;
identifier: ID (PATH_SEPARATOR ID)*;
matchID: ID;
consumeString: CONSUMPTION_STRING;
produceString: PRODUCTION_STRING;
consumeInteger: CONSUMPTION_INT;
produceInteger: PRODUCTION_INT;
namedPrimitive: 
    NAMED_PRIMITIVE_RETURN | NAMED_PRIMITIVE_VARIABLES
    | NAMED_PRIMITIVE_EOF | NAMED_PRIMITIVE_NIL;
move: CARET;
peek: AT;
next: UNDERSCORE;
label: ID COLON;
produceVerbatim: VERBATIM;
quote: SINGLE_QUOTE termValue;
interpolation: DOLLAR termValue;

DOLLAR: '$';
COMMA: ',';
AT: '@';
HASH: '#';
DOUBLE_PLUS: '++';
CONSUMPTION_STRING: TILDE STRING {setText(getText().substring(2, getText().length() - 1));};
PRODUCTION_STRING: STRING {setText(getText().substring(1, getText().length() - 1));};
CONSUMPTION_INT: TILDE INT {setText(getText().substring(1));};
PRODUCTION_INT: INT;
fragment STRING:   
    '"' (ESC_CHAR | ~'"')* '"'
    {setText(getText().substring(1, getText().length() - 1));};
fragment INT :
    '-'? DIGIT+
    ;

IO_SCOPE: 
    '\\' DIGIT+ 
    {setText(getText().substring(1));}
    ;

SINGLE_QUOTE: '\'';
EXCLAMATION: '!';
CARET: '^';
fragment TILDE: '~';
PATH_SEPARATOR: '::';
DOUBLE_DOT: '..';
SEMICOLON: ';';
NAMED_PRIMITIVE_RETURN: 'ret';
NAMED_PRIMITIVE_VARIABLES: 'vars';
NAMED_PRIMITIVE_EOF: 'eof';
NAMED_PRIMITIVE_NIL: 'nil';
KEYWORD_DEFINE: 'def';
KEYWORD_USE: 'use';
KEYWORD_FROM: 'from';
KEYWORD_TO: 'to';
KEYWORD_VAR: 'var';
KEYWORD_REST: 'rest';
KEYWORD_GOTO: 'goto';
KEYWORD_FILE: 'file';
fragment GREATER_THAN: '>';
fragment LESS_THAN: '<';
PIPELINE: GREATER_THAN GREATER_THAN;
PIPELINE_FROM: COLON GREATER_THAN GREATER_THAN;
PIPELINE_TO: GREATER_THAN GREATER_THAN COLON;
PIPE: '|';
UNDERSCORE: '_';
ASTERIX: '*';
PRECEDENCE_OPERATOR: LESS_THAN /*| '='*/ /*Commented due to EQUALS operator*/ | GREATER_THAN;
COLON: ':';
DOT: '.';
EQUALS: '=';
OPEN_SQ_BRACKET: '[';
CLOSE_SQ_BRACKET: ']';
OPEN_PARENTHESIS: '(';
CLOSE_PARENTHESIS: ')';
OPEN_BRACKET: '{';
CLOSE_BRACKET: '}';
fragment ESC_CHAR
    :   '\\'
        (   'n'
        |   'r'
        |   't'
        |   'b'
        |   'f'
        |   '"'
        |   '\\'
        )
     ;
fragment DIGIT: ('0'..'9');
KW_IF : 'if';
fragment LETTER: ([a-z]|[A-Z]);
ID : LETTER (LETTER | DIGIT | UNDERSCORE)*;
WS : [ \t\r\n]+ -> skip ;
SINGLELINE_COMMENT
 : '//' ~('\r' | '\n')* -> skip
 ;
MULTI_LINE_COMMENT
 : '/*' .*? '*/' -> skip
 ;

VERBATIM : NESTED_VERBATIM {setText(getText().substring(1, getText().length() - 1));};
fragment NESTED_VERBATIM :
      '`'
      (NESTED_VERBATIM | VERBATIM_ESC_CHAR | ~'´')*?
      '´'
   ;
fragment VERBATIM_ESC_CHAR
    :   ('\\' '´')
    ;