set nocompatible
filetype plugin on
syntax on

:set number
:set nowrap
:set scrolloff=3 
:set relativenumber

map <F6> :setlocal spell! spelllang=en_us<CR>
map <F7> :set hlsearch<Enter>
map <F8> :noh<Enter>
map <F9> :Goyo<Enter>
map <F10> :set wrap<Enter>:set lbr<Enter>

inoremap <Tab> <Esc>/<+><Enter>"_c4l
nnoremap j gj
nnoremap k gk
autocmd FileType java inoremap /s System.out.println();<+><Esc>0f(a
autocmd FileType java inoremap " ""<Esc>i
autocmd FileType java inoremap ( ()<Esc>i
autocmd FileType java inoremap ) <Esc>la
autocmd FileType java inoremap /f for(){<Enter>}<Esc>O<Space><Space><+><Esc>k0f(a
autocmd FileType java inoremap /i if(){<Enter>}<Esc>O<Space><Space><+><Esc>k0f(a
autocmd FileType java inoremap /e <Esc>jaelse{<Enter>}<Esc>ko<Space><Space>
autocmd FileType java inoremap /w while(){<Enter>}<Esc>O<Space><Space><+><Esc>k0f(a
autocmd FileType java inoremap ' <Esc>la

colorscheme nord

call plug#begin('~/.vim/plugged')
Plug 'junegunn/goyo.vim'

call plug#end()
