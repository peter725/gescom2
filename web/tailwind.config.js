const { createGlobPatternsForDependencies } = require('@nrwl/angular/tailwind');
const { join } = require('path');
const defaultTheme = require('tailwindcss/defaultTheme');

module.exports = {
  // prefix:'tw',
  content: [
    join(__dirname, 'src/**/!(*.stories|*.spec).{ts,html}'),
    ...createGlobPatternsForDependencies(__dirname),
  ],
  theme: {
    extend: {
      colors: {
        'text-primary-color': 'var(----text-primary-color)',
        'text-primary-lighter-color': 'var(--text-primary-lighter-color)',
        'text-primary-darker-color': 'var(--text-primary-darker-color)',
        'text-accent-color': 'var(--text-accent-color)',
        'text-accent-lighter-color': 'var(--text-accent-lighter-color)',
        'text-accent-darker-color': 'var(--text-accent-darker-color)',
      },
    },
    screens: {
      'xs': '320px', // Bellow sm
      ...defaultTheme.screens,
    },
  },
  plugins: [],
  safelist: [
    'grid-rows-2',
    'grid-rows-3',
    'grid-rows-4',
    'grid-rows-5',
    'grid-cols-1',
    'sm:grid-cols-1',
    'md:grid-cols-1',
    'xl:grid-cols-1',
    '2xl:grid-cols-1',
    'grid-cols-2',
    'sm:grid-cols-2',
    'md:grid-cols-2',
    'xl:grid-cols-2',
    '2xl:grid-cols-2',
    'grid-cols-3',
    'sm:grid-cols-3',
    'md:grid-cols-3',
    'xl:grid-cols-3',
    '2xl:grid-cols-3',
    'grid-cols-4',
    'sm:grid-cols-4',
    'md:grid-cols-4',
    'xl:grid-cols-4',
    '2xl:grid-cols-4',
    'grid-cols-5',
    'sm:grid-cols-5',
    'md:grid-cols-5',
    'xl:grid-cols-5',
    '2xl:grid-cols-5',
    'grid-cols-6',
    'sm:grid-cols-6',
    'md:grid-cols-6',
    'xl:grid-cols-6',
    '2xl:grid-cols-6',
  ],
};
