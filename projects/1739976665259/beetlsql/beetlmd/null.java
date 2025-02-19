sample
===

select #use("cols")# from users where #use("condition")#

cols
===
        `id`

updateSample
===
        `id=#id#`

condition
===
    1 = 1
    @if(!isEmpty(id)){
      and `id`=#id#
    @}
